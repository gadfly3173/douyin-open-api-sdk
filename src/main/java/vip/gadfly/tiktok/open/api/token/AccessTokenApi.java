package vip.gadfly.tiktok.open.api.token;


import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.core.utils.StringUtil;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.base.ApiResponse;

/**
 * accessToken处理
 *
 * @author OF
 * @date 2017年10月16日
 */
public class AccessTokenApi extends ApiBase {
    private static final String TOKEN_URL = AppConfig.getInstance().httpUrl + "/oauth/access_token";
    private static final String TOKEN_CLIENT_URL = AppConfig.getInstance().httpUrl + "/oauth/client_token";

    /**
     * 根据 code 请求 accessToken
     * (默认以当前 openId 为 key 将 token 缓存本地)
     *
     * @param code 授权码
     * @return
     */
    public AccessTokenResult get(String code) {
        AccessTokenParam param = new AccessTokenParam();
        param.setCode(code);
        String url = TOKEN_URL + "?" + param.getUrlParam();
        ApiResponse response = sendGet(url);
        AccessTokenResult result = response.dataToBean(AccessTokenResult.class);
        result.saveCache(result.getOpenId());
        return result;
    }

    /**
     * 获取 clientAccessToken
     * (默认以当前 appId 为 key 将 token 缓存本地)
     *
     * @return
     */
    public AccessTokenResult get() {
        AccessTokenParam param = new AccessTokenParam();
        param.setGrantType(AccessTokenParam.GRANT_TYPE_CLIENT);
        String url = TOKEN_CLIENT_URL + "?" + param.getClientUrlParam();
        ApiResponse response = sendGet(url);
        AccessTokenResult result = response.dataToBean(AccessTokenResult.class);
        result.saveCache(AppConfig.getAppId());
        return result;
    }

    /**
     * 刷新 accessToken
     *
     * @param accessToken 需要刷新的 token
     * @return
     */
    public AccessTokenResult refresh(String accessToken) {
        String cacheKey = getCacheKey();
        AccessTokenParam param = new AccessTokenParam();
        param.setGrantType(AccessTokenParam.GRANT_TYPE_REFRESH);
        param.setRefreshToken(accessToken);
        String url = TOKEN_URL + "?" + param.getRefreshUrlParam();
        ApiResponse response = sendGet(url);
        AccessTokenResult result = response.dataToBean(AccessTokenResult.class);
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
    public String get(boolean isRefresh) {
        AccessTokenConfig config = new AccessTokenConfig();
        if (!StringUtil.isEmpty(getOpenId())) {
            return config.getAccessToken(getOpenId(), isRefresh);
        } else {
            return config.getAccessToken(getAppId(), isRefresh);
        }

    }

    public AccessTokenApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public AccessTokenApi withOpenId(String openId) {
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
