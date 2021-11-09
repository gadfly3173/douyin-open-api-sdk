package vip.gadfly.tiktok.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.config.TtOpHostConfig;

import static vip.gadfly.tiktok.config.TtOpHostConfig.*;

/**
 * @author Gadfly
 * @since 2021-09-18 14:30
 */
public interface TtOpApiUrl {
    /**
     * 得到api完整地址.
     *
     * @param config 抖音开发者应用配置
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

    /**
     * 用户管理
     */
    @AllArgsConstructor
    @Getter
    enum UserInfo implements TtOpApiUrl {
        /**
         * 获取用户信息
         */
        GET_USER_INFO_URL(TIKTOK_OPEN_API_HOST_URL, "/oauth/userinfo/?open_id=%s&access_token=%s"),
        /**
         * 获取粉丝列表
         */
        GET_FANS_LIST_URL(TIKTOK_OPEN_API_HOST_URL, "/fans/list/?open_id=%s&cursor=%s&count=%s&access_token=%s"),
        /**
         * 获取关注列表
         */
        GET_FOLLOWING_LIST_URL(TIKTOK_OPEN_API_HOST_URL, "/following/list/?open_id=%s&cursor=%s&count=%s&access_token=%s"),
        /**
         * 粉丝判断
         */
        GET_FANS_CHECK_URL(TIKTOK_OPEN_API_HOST_URL, "/following/list/?open_id=%s&follower_open_id=%s&access_token=%s"),
        ;

        private final String prefix;
        private final String path;

    }

    /**
     * 账号授权
     */
    @AllArgsConstructor
    @Getter
    enum OAuth2 implements TtOpApiUrl {
        /**
         * 用code换取oauth2的access token.
         */
        OAUTH2_ACCESS_TOKEN_URL(TIKTOK_OPEN_API_HOST_URL, "/oauth/access_token/"),
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
         * oauth2授权的url链接.
         */
        CONNECT_OAUTH2_AUTHORIZE_URL(TIKTOK_OPEN_API_HOST_URL, "/platform/oauth/connect/?client_key=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&optionalScope=%s"),
        /**
         * oauth2静默授权的url链接.
         */
        CONNECT_SILENT_OAUTH2_AUTHORIZE_URL(TIKTOK_SILENT_OPEN_API_HOST_URL, "/oauth/authorize/v2/?client_key=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s"),
        ;

        private final String prefix;
        private final String path;

    }

    /**
     * 视频管理
     */
    @AllArgsConstructor
    @Getter
    enum Video implements TtOpApiUrl {
        /**
         * 创建抖音视频
         */
        CREATE_TIKTOK_VIDEO_URL(TIKTOK_OPEN_API_HOST_URL, "/video/create/?open_id=%s&access_token=%s"),
        /**
         * 上传抖音视频.
         */
        UPLOAD_TIKTOK_VIDEO_URL(TIKTOK_OPEN_API_HOST_URL, "/video/upload/?open_id=%s&access_token=%s"),
        /**
         * 查询抖音指定视频数据
         */
        GET_TIKTOK_SPECIFIC_VIDEO_DATA_URL(TIKTOK_OPEN_API_HOST_URL, "/video/data/?open_id=%s&access_token=%s"),
        ;

        private final String prefix;
        private final String path;

    }
}
