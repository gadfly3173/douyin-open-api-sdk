package vip.gadfly.tiktok.open.api.token;


import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.common.TtOpApiResponse;

/**
 * accessToken处理
 *
 * @author OF
 * @date 2017年10月16日
 */
public class TtOpAccessTokenApi extends AbstractTtOpApiBase {
    private final String TOKEN_URL = this.getHttpUrl() + "/oauth/access_token";
    private final String TOKEN_CLIENT_URL = this.getHttpUrl() + "/oauth/client_token";

    /**
     * 根据 code 请求 accessToken
     * (默认以当前 openId 为 key 将 token 缓存本地)
     *
     * @param code 授权码
     * @return
     */
    public TtOpAccessTokenResult getAccessTokenResult(String code) {
        TtOpAccessTokenParam param = new TtOpAccessTokenParam();
        param.setCode(code).setGrantType(TtOpAccessTokenParam.GRANT_TYPE_CODE).setAppId(this.getAppId())
                .setAppSecret(this.getTtOpConfigStorage().getAppSecret());
        String url = TOKEN_URL + "?" + param.getUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpAccessTokenResult result = response.dataToBean(TtOpAccessTokenResult.class);
        result.saveCache(result.getOpenId());
        return result;
    }

    /**
     * 获取 clientAccessToken
     * (默认以当前 appId 为 key 将 token 缓存本地)
     *
     * @return
     */
    public TtOpAccessTokenResult getClientTokenResult() {
        TtOpAccessTokenParam param = new TtOpAccessTokenParam();
        param.setGrantType(TtOpAccessTokenParam.GRANT_TYPE_CLIENT).setAppId(this.getAppId())
                .setAppSecret(this.getTtOpConfigStorage().getAppSecret());
        String url = TOKEN_CLIENT_URL + "?" + param.getClientUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpAccessTokenResult result = response.dataToBean(TtOpAccessTokenResult.class);
        return result;
    }

    /**
     * 获取 jsapi_ticket
     *
     * @return
     */
    public TtOpAccessTokenResult getJsapiTicketResult() {
        TtOpAccessTokenParam param = new TtOpAccessTokenParam();
        param.setAccessToken(this.getTicket(TtOpTicketType.CLIENT));
        String url = TOKEN_CLIENT_URL + "?" + param.getJsapiUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpAccessTokenResult result = response.dataToBean(TtOpAccessTokenResult.class);
        return result;
    }

    /**
     * 刷新 accessToken
     *
     * @param refreshToken 需要刷新的 token
     * @return
     */
    public TtOpAccessTokenResult refreshAccessToken(String refreshToken) {
        String cacheKey = getCacheKey();
        TtOpAccessTokenParam param = new TtOpAccessTokenParam();
        param.setGrantType(TtOpAccessTokenParam.GRANT_TYPE_REFRESH).setRefreshToken(refreshToken)
                .setAppId(this.getAppId()).setAppSecret(this.getTtOpConfigStorage().getAppSecret());
        String url = TOKEN_URL + "?" + param.getRefreshUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpAccessTokenResult result = response.dataToBean(TtOpAccessTokenResult.class);
        result.saveCache(cacheKey);
        return result;
    }

    /**
     * 根据 openId 缓存中获取 accessToken
     * 根据 appId 缓存中获取 clientAccessToken
     *
     * @param isRefresh
     * @return
     */
    public String getAccessToken(boolean isRefresh) {
        TiktokOpenAccessTokenConfig config = TiktokOpenAccessTokenConfig.getInstance();
        return config.getAccessToken(getCacheKey(), isRefresh);

    }

    public TtOpAccessTokenApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TtOpAccessTokenApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    /**
     * scope
     *
     * @return
     */
    public String scope() {
        return null;
    }
}
