package vip.gadfly.tiktok.config.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.core.redis.BaseTtOpRedisOps;
import vip.gadfly.tiktok.open.bean.oauth2.TtOpAccessTokenResult;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 基于Redis的微信配置provider.
 *
 * @author nickwong
 * @author Gadfly
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TtOpRedisConfigImpl extends TtOpDefaultConfigImpl {
    private static final long serialVersionUID = -988502871997239733L;

    private static final String ACCESS_TOKEN_KEY_TPL = "%s:access_token:%s:%s";
    private static final String REFRESH_TOKEN_KEY_TPL = "%s:refresh_token:%s:%s";
    private static final String TICKET_KEY_TPL = "%s:ticket:%s:%s";
    private static final String LOCK_KEY_TPL = "%s:lock:%s:";

    private final BaseTtOpRedisOps redisOps;
    private final String keyPrefix;

    private String lockKey;

    public TtOpRedisConfigImpl(BaseTtOpRedisOps redisOps, String keyPrefix) {
        this.redisOps = redisOps;
        this.keyPrefix = keyPrefix;
    }

    /**
     * 每个公众号生成独有的存储key.
     */
    @Override
    public void setAppId(String appId) {
        super.setAppId(appId);
        this.lockKey = String.format(LOCK_KEY_TPL, this.keyPrefix, appId);
        jsapiTicketLock = this.redisOps.getLock(lockKey.concat("jsapiTicketLock"));
        clientTicketLock = this.redisOps.getLock(lockKey.concat("clientTicketLock"));
    }

    @Override
    public String getAccessToken(String openid) {
        return redisOps.getValue(this.getAccessTokenRedisKey(openid));
    }

    @Override
    public Lock getAccessTokenLock(String openid) {
        return redisOps.getLock(lockKey.concat("accessTokenLock:").concat(openid));
    }

    @Override
    public boolean isAccessTokenExpired(String openid) {
        Long expire = redisOps.getExpire(this.getAccessTokenRedisKey(openid));
        return expire == null || expire < 2;
    }

    @Override
    public synchronized void updateAccessToken(TtOpAccessTokenResult accessToken) {
        redisOps.setValue(this.getAccessTokenRedisKey(accessToken.getOpenId()),
                accessToken.getAccessToken(), accessToken.getExpiresIn(), TimeUnit.SECONDS);
    }

    @Override
    public synchronized void updateAccessToken(String openid, String accessToken, int expiresInSeconds) {
        redisOps.setValue(this.getAccessTokenRedisKey(openid), accessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireAccessToken(String openid) {
        redisOps.expire(this.getAccessTokenRedisKey(openid), 0, TimeUnit.SECONDS);
    }

    @Override
    public String getRefreshToken(String openid) {
        return redisOps.getValue(this.getRefreshTokenRedisKey(openid));
    }

    @Override
    public Lock getRefreshTokenLock(String openid) {
        return redisOps.getLock(lockKey.concat("refreshTokenLock:").concat(openid));
    }

    @Override
    public boolean isRefreshTokenExpired(String openid) {
        Long expire = redisOps.getExpire(this.getRefreshTokenRedisKey(openid));
        return expire == null || expire < 2;
    }

    @Override
    public synchronized void updateRefreshToken(TtOpAccessTokenResult refreshToken) {
        updateRefreshToken(this.getRefreshTokenRedisKey(refreshToken.getOpenId()), refreshToken.getRefreshToken(),
                refreshToken.getRefreshExpiresIn() < 10
                        ? refreshToken.getExpiresIn()
                        : refreshToken.getRefreshExpiresIn());
    }

    @Override
    public synchronized void updateRefreshToken(String openid, String refreshToken, int expiresInSeconds) {
        redisOps.setValue(this.getRefreshTokenRedisKey(openid), refreshToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireRefreshToken(String openid) {
        redisOps.expire(this.getRefreshTokenRedisKey(openid), 0, TimeUnit.SECONDS);
    }

    private String getAccessTokenRedisKey(String openid) {
        return String.format(ACCESS_TOKEN_KEY_TPL, this.keyPrefix, appId, openid);
    }

    private String getRefreshTokenRedisKey(String openid) {
        return String.format(REFRESH_TOKEN_KEY_TPL, this.keyPrefix, appId, openid);
    }

    private String getTicketRedisKey(TtOpTicketType type) {
        return String.format(TICKET_KEY_TPL, this.keyPrefix, appId, type.getCode());
    }

    @Override
    public String getTicket(TtOpTicketType type) {
        return redisOps.getValue(this.getTicketRedisKey(type));
    }

    @Override
    public boolean isTicketExpired(TtOpTicketType type) {
        return redisOps.getExpire(this.getTicketRedisKey(type)) < 2;
    }

    @Override
    public synchronized void updateTicket(TtOpTicketType type, String jsapiTicket, int expiresInSeconds) {
        redisOps.setValue(this.getTicketRedisKey(type), jsapiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public void expireTicket(TtOpTicketType type) {
        redisOps.expire(this.getTicketRedisKey(type), 0, TimeUnit.SECONDS);
    }

}
