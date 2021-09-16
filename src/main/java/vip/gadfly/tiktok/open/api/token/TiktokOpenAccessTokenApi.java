package vip.gadfly.tiktok.open.api.token;


import vip.gadfly.tiktok.core.utils.StringUtil;
import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.TiktokOpenApiResponse;

/**
 * accessToken处理
 *
 * @author OF
 * @date 2017年10月16日
 */
public class TiktokOpenAccessTokenApi extends AbstractTiktokOpenApiBase {
    private final String TOKEN_URL = getHttpUrl() + "/oauth/access_token";
    private final String TOKEN_CLIENT_URL = getHttpUrl() + "/oauth/client_token";

    /**
     * 根据 code 请求 accessToken
     * (默认以当前 openId 为 key 将 token 缓存本地)
     *
     * @param code 授权码
     * @return
     */
    public TiktokOpenAccessTokenResult getAccessTokenResult(String code) {
        TiktokOpenAccessTokenParam param = new TiktokOpenAccessTokenParam();
        param.setCode(code).setGrantType(TiktokOpenAccessTokenParam.GRANT_TYPE_CODE);
        String url = TOKEN_URL + "?" + param.getUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenAccessTokenResult result = response.dataToBean(TiktokOpenAccessTokenResult.class);
        result.saveCache(result.getOpenId());
        return result;
    }

    /**
     * 获取 clientAccessToken
     * (默认以当前 appId 为 key 将 token 缓存本地)
     *
     * @return
     */
    public TiktokOpenAccessTokenResult getClientTokenResult() {
        TiktokOpenAccessTokenParam param = new TiktokOpenAccessTokenParam();
        param.setGrantType(TiktokOpenAccessTokenParam.GRANT_TYPE_CLIENT);
        String url = TOKEN_CLIENT_URL + "?" + param.getClientUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenAccessTokenResult result = response.dataToBean(TiktokOpenAccessTokenResult.class);
        result.saveCache(getAppId());
        return result;
    }

    /**
     * 刷新 accessToken
     *
     * @param accessToken 需要刷新的 token
     * @return
     */
    public TiktokOpenAccessTokenResult refreshAccessToken(String accessToken) {
        String cacheKey = getCacheKey();
        TiktokOpenAccessTokenParam param = new TiktokOpenAccessTokenParam();
        param.setGrantType(TiktokOpenAccessTokenParam.GRANT_TYPE_REFRESH);
        param.setRefreshToken(accessToken);
        String url = TOKEN_URL + "?" + param.getRefreshUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenAccessTokenResult result = response.dataToBean(TiktokOpenAccessTokenResult.class);
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

    public TiktokOpenAccessTokenApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TiktokOpenAccessTokenApi withOpenId(String openId) {
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
