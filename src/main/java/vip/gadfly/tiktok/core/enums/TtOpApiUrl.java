package vip.gadfly.tiktok.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.config.TtOpHostConfig;

import static vip.gadfly.tiktok.config.TtOpHostConfig.TIKTOK_OPEN_API_HOST_URL;
import static vip.gadfly.tiktok.config.TtOpHostConfig.buildUrl;

/**
 * @author Gadfly
 * @since 2021-09-18 14:30
 */
public interface TtOpApiUrl {
    /**
     * 得到api完整地址.
     *
     * @param config 微信公众号配置
     * @return api地址
     */
    default String getUrl(TtOpConfigStorage config) {
        TtOpHostConfig hostConfig = null;
        if (config != null) {
            hostConfig = config.getHostConfig();
        }
        return buildUrl(hostConfig, this.getPrefix(), this.getPath());
    }

    /**
     * the path
     *
     * @return path
     */
    String getPath();

    /**
     * the prefix
     *
     * @return prefix
     */
    String getPrefix();

    @AllArgsConstructor
    @Getter
    enum OAuth2 implements TtOpApiUrl {
        /**
         * 用code换取oauth2的access token.
         */
        OAUTH2_ACCESS_TOKEN_URL(TIKTOK_OPEN_API_HOST_URL, " /oauth/access_token/"),
        /**
         * 刷新oauth2的access token.
         */
        OAUTH2_REFRESH_TOKEN_URL(TIKTOK_OPEN_API_HOST_URL, "/oauth/refresh_token/"),
        /**
         * 刷新oauth2的refresh token.
         */
        OAUTH2_RENEW_REFRESH_TOKEN_URL(TIKTOK_OPEN_API_HOST_URL, "/oauth/renew_refresh_token/"),
        /**
         * 获取client token.
         */
        OAUTH2_CLIENT_TOKEN_URL(TIKTOK_OPEN_API_HOST_URL, "/oauth/client_token/"),
        /**
         * 获取jsapi ticket.
         */
        OAUTH2_JSAPI_TICKET_URL(TIKTOK_OPEN_API_HOST_URL, "/js/getticket/?access_token=%s"),
        /**
         * oauth2授权的url连接.
         */
        CONNECT_OAUTH2_AUTHORIZE_URL(TIKTOK_OPEN_API_HOST_URL, "/platform/oauth/connect/?client_key=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&optionalScope=%s");

        private final String prefix;
        private final String path;

    }
}
