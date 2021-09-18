package vip.gadfly.tiktok.config.impl;

import lombok.Data;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.config.TtOpHostConfig;
import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.open.bean.oauth2.TtOpAccessTokenResult;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化.
 *
 * @author chanjarster
 */
@Data
public class TtOpDefaultConfigImpl implements TtOpConfigStorage, Serializable {
    private static final long serialVersionUID = -1L;

    protected volatile String appId;
    protected volatile String appSecret;
    protected volatile String accessToken;
    protected volatile long expiresTime;
    protected volatile String refreshToken;
    protected volatile long refreshExpiresTime;
    protected volatile String oauth2redirectUri;

    protected volatile int retrySleepMillis = 1000;
    protected volatile int maxRetryTimes = 5;

    protected volatile String jsapiTicket;
    protected volatile long jsapiTicketExpiresTime;

    protected volatile String clientTicket;
    protected volatile long clientTicketExpiresTime;

    protected volatile Lock accessTokenLock = new ReentrantLock();
    protected volatile Lock refreshTokenLock = new ReentrantLock();
    protected volatile Lock jsapiTicketLock = new ReentrantLock();
    protected volatile Lock clientTicketLock = new ReentrantLock();

    protected volatile File tmpDirFile;

    private TtOpHostConfig hostConfig = new TtOpHostConfig();

    @Override
    public String getAccessToken(String openid) {
        return this.accessToken;
    }

    @Override
    public Lock getAccessTokenLock(String openid) {
        return this.accessTokenLock;
    }

    @Override
    public boolean isAccessTokenExpired(String openid) {
        return System.currentTimeMillis() > this.expiresTime;
    }

    @Override
    public synchronized void updateAccessToken(TtOpAccessTokenResult accessToken) {
        updateAccessToken(accessToken.getOpenId(), accessToken.getAccessToken(), accessToken.getExpiresIn());
    }

    @Override
    public synchronized void updateAccessToken(String openid, String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }

    @Override
    public void expireAccessToken(String openid) {
        this.expiresTime = 0;
    }

    @Override
    public String getRefreshToken(String openid) {
        return this.refreshToken;
    }

    @Override
    public Lock getRefreshTokenLock(String openid) {
        return this.refreshTokenLock;
    }

    @Override
    public boolean isRefreshTokenExpired(String openid) {
        return System.currentTimeMillis() > this.refreshExpiresTime;
    }

    @Override
    public synchronized void updateRefreshToken(TtOpAccessTokenResult refreshToken) {
        updateRefreshToken(refreshToken.getOpenId(), refreshToken.getRefreshToken(),
                refreshToken.getRefreshExpiresIn() < 10
                        ? refreshToken.getExpiresIn()
                        : refreshToken.getRefreshExpiresIn());
    }

    @Override
    public synchronized void updateRefreshToken(String openid, String refreshToken, int expiresInSeconds) {
        this.refreshToken = refreshToken;
        this.refreshExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }

    @Override
    public void expireRefreshToken(String openid) {
        this.refreshExpiresTime = 0;
    }

    @Override
    public String getTicket(TtOpTicketType type) {
        switch (type) {
            case CLIENT:
                return this.clientTicket;
            case JSAPI:
                return this.jsapiTicket;
            default:
                return null;
        }
    }

    public void setTicket(TtOpTicketType type, String ticket) {
        switch (type) {
            case CLIENT:
                this.clientTicket = ticket;
            case JSAPI:
                this.jsapiTicket = ticket;
                break;
            default:
        }
    }

    @Override
    public Lock getTicketLock(TtOpTicketType type) {
        switch (type) {
            case CLIENT:
                return this.clientTicketLock;
            case JSAPI:
                return this.jsapiTicketLock;
            default:
                return null;
        }
    }

    @Override
    public boolean isTicketExpired(TtOpTicketType type) {
        switch (type) {
            case CLIENT:
                return System.currentTimeMillis() > this.clientTicketExpiresTime;
            case JSAPI:
                return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
            default:
                return false;
        }
    }

    @Override
    public synchronized void updateTicket(TtOpTicketType type, String ticket, int expiresInSeconds) {
        switch (type) {
            case JSAPI:
                this.jsapiTicket = ticket;
                // 预留200秒的时间
                this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
                break;
            case CLIENT:
                this.clientTicket = ticket;
                // 预留200秒的时间
                this.clientTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
                break;
            default:
        }
    }

    @Override
    public void expireTicket(TtOpTicketType type) {
        switch (type) {
            case JSAPI:
                this.jsapiTicketExpiresTime = 0;
                break;
            case CLIENT:
                this.clientTicketExpiresTime = 0;
                break;
            default:
        }
    }

    @Override
    public boolean autoRefreshToken() {
        return true;
    }

    @Override
    public TtOpHostConfig getHostConfig() {
        return this.hostConfig;
    }

    @Override
    public void setHostConfig(TtOpHostConfig hostConfig) {
        this.hostConfig = hostConfig;
    }

}
