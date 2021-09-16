package vip.gadfly.tiktok.config;

import vip.gadfly.tiktok.core.enums.TiktokOpenTicketType;

import java.io.File;
import java.util.concurrent.locks.Lock;

/**
 * 微信客户端配置存储.
 *
 * @author chanjarster
 */
public interface TiktokOpenConfigStorage {
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
    String getTicket(TiktokOpenTicketType type);

    /**
     * Gets ticket lock.
     *
     * @param type the type
     * @return the ticket lock
     */
    Lock getTicketLock(TiktokOpenTicketType type);

    /**
     * Is ticket expired boolean.
     *
     * @param type the type
     * @return the boolean
     */
    boolean isTicketExpired(TiktokOpenTicketType type);

    /**
     * 强制将ticket过期掉.
     *
     * @param type the type
     */
    void expireTicket(TiktokOpenTicketType type);

    /**
     * 更新ticket.
     * 应该是线程安全的
     *
     * @param type             ticket类型
     * @param ticket           新的ticket值
     * @param expiresInSeconds 过期时间，以秒为单位
     */
    void updateTicket(TiktokOpenTicketType type, String ticket, int expiresInSeconds);

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
    TiktokOpenHostConfig getHostConfig();

    /**
     * 设置微信接口地址域名部分的自定义设置信息.
     *
     * @param hostConfig host config
     */
    void setHostConfig(TiktokOpenHostConfig hostConfig);
}
