package vip.gadfly.tiktok.open.api;

import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.open.bean.oauth2.TtOpAccessTokenResult;

/**
 * @author Gadfly
 * @since 2021-09-18 14:18
 */
public interface TtOpOAuth2Service {
    /**
     * 构造oauth2授权的url连接.
     * 详情请见: https://open.douyin.com/platform/doc/6848834666171009035
     *
     * @param redirectUri 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
     * @param scope       scope
     * @param state       state
     * @return url
     */
    String buildAuthorizationUrl(String redirectUri, String scope, String state);

    /**
     * 使用授权码换取用户信息的接口调用凭据.
     *
     * @param authorizationCode code
     */
    TtOpAccessTokenResult getAccessTokenByAuthorizationCode(String authorizationCode);

    /**
     * 刷新用户的access token
     *
     * @param openid openid
     * @return token
     */
    TtOpAccessTokenResult refreshAccessToken(String openid);

    /**
     * 刷新用户的refresh token
     *
     * @param openid openid
     * @return token
     */
    TtOpAccessTokenResult renewRefreshToken(String openid);

    /**
     * 获取client token
     *
     * @return token
     */
    String getClientToken(boolean forceRefresh);

    /**
     * 获取jsapi ticket
     *
     * @return token
     */
    String getJsapiTicket(boolean forceRefresh);

    /**
     * 获取ticket
     *
     * @return token
     */
    String getTicket(TtOpTicketType type, boolean forceRefresh);
}
