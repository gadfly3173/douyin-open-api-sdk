package vip.gadfly.tiktok.config;

import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.open.bean.oauth2.TtOpAccessTokenResult;

import java.io.File;
import java.util.concurrent.locks.Lock;

/**
 * 微信客户端配置存储.
 *
 * @author chanjarster
 */
public interface TtOpConfigStorage {
    /**
     * Gets access token.
     *
     * @return the access token
     */
    String getAccessToken(String openid);

    /**
     * Gets access token lock.
     *
     * @return the access token lock
     */
    Lock getAccessTokenLock(String openid);

    /**
     * Is access token expired boolean.
     *
     * @return the boolean
     */
    boolean isAccessTokenExpired(String openid);

    /**
     * 强制将access token过期掉.
     */
    void expireAccessToken(String openid);

    /**
     * 应该是线程安全的.
     *
     * @param accessToken 要更新的WxAccessToken对象
     */
    void updateAccessToken(TtOpAccessTokenResult accessToken);

    /**
     * 应该是线程安全的.
     *
     * @param accessToken      新的accessToken值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    void updateAccessToken(String openid, String accessToken, int expiresInSeconds);

    /**
     * Gets access token.
     *
     * @return the access token
     */
    String getRefreshToken(String openid);

    /**
     * Gets access token lock.
     *
     * @return the access token lock
     */
    Lock getRefreshTokenLock(String openid);

    /**
     * Is access token expired boolean.
     *
     * @return the boolean
     */
    boolean isRefreshTokenExpired(String openid);

    /**
     * 强制将access token过期掉.
     */
    void expireRefreshToken(String openid);

    /**
     * 应该是线程安全的.
     *
     * @param accessToken 要更新的WxAccessToken对象
     */
    void updateRefreshToken(TtOpAccessTokenResult accessToken);

    /**
     * 应该是线程安全的.
     *
     * @param accessToken      新的accessToken值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    void updateRefreshToken(String openid, String accessToken, int expiresInSeconds);

    /**
     * Gets app id.
     *
     * @return the app id
     */
    String getAppId();

    /**
     * Gets secret.
     *
     * @return the secret
     */
    String getAppSecret();

    /**
     * Gets oauth 2 redirect uri.
     *
     * @return the oauth 2 redirect uri
     */
    String getOauth2redirectUri();

    /**
     * Gets ticket.
     *
     * @param type the type
     * @return the ticket
     */
    String getTicket(TtOpTicketType type);

    /**
     * Gets ticket lock.
     *
     * @param type the type
     * @return the ticket lock
     */
    Lock getTicketLock(TtOpTicketType type);

    /**
     * Is ticket expired boolean.
     *
     * @param type the type
     * @return the boolean
     */
    boolean isTicketExpired(TtOpTicketType type);

    /**
     * 强制将ticket过期掉.
     *
     * @param type the type
     */
    void expireTicket(TtOpTicketType type);

    /**
     * 更新ticket.
     * 应该是线程安全的
     *
     * @param type             ticket类型
     * @param ticket           新的ticket值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    void updateTicket(TtOpTicketType type, String ticket, int expiresInSeconds);

    /**
     * Gets tmp dir file.
     *
     * @return the tmp dir file
     */
    File getTmpDirFile();

    /**
     * 是否自动刷新token.
     *
     * @return the boolean
     */
    boolean autoRefreshToken();

    /**
     * 得到微信接口地址域名部分的自定义设置信息.
     *
     * @return the host config
     */
    TtOpHostConfig getHostConfig();

    /**
     * 设置微信接口地址域名部分的自定义设置信息.
     *
     * @param hostConfig host config
     */
    void setHostConfig(TtOpHostConfig hostConfig);
}
