package vip.gadfly.tiktok.open.api.oauth;


import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.base.TiktokOpenApiBase;

/**
 * 网页授权
 *
 * @author hubusi
 * @date 2019/11/10
 */
public class TiktokOpenOauthApi extends TiktokOpenApiBase {
    public String CONNECTION_URL = getHttpUrl() + "/platform/oauth/connect/";

    /**
     * 自定义 TiktokOpenOauthConnectParam 获取抖音扫一扫登陆URL
     *
     * @param param 自定义 TiktokOpenOauthConnectParam
     * @return
     */
    public String getScanUrl(TiktokOpenOauthConnectParam param) {
        return CONNECTION_URL + "?" + param.getUrlParam();
    }

    /**
     * 根据 redirectUri 获取抖音扫一扫登陆URL
     *
     * @param redirectUri 回调地址
     * @return
     */
    public String getScanUrl(String redirectUri) {
        TiktokOpenOauthConnectParam param = new TiktokOpenOauthConnectParam();
        param.setRedirectUri(redirectUri);
        return CONNECTION_URL + "?" + param.getUrlParam();
    }

    /**
     * 获取抖音扫一扫登陆URL
     *
     * @return
     */
    public String getScanUrl() {
        TiktokOpenOauthConnectParam param = new TiktokOpenOauthConnectParam();
        return CONNECTION_URL + "?" + param.getUrlParam();
    }


    /**
     * 用于获取用户允许授权后，重定向到 redirect_uri 的网址的code值
     *
     * @param scanBackUrl 回调地址
     * @return
     */
    public TiktokOpenOauthConnectResult getResult(String scanBackUrl) {
        return JsonUtil.jsonToBean(scanBackUrl, TiktokOpenOauthConnectResult.class);
    }


    public TiktokOpenApiBase withAccessToken(String accessToken) {
        return null;
    }

    public TiktokOpenApiBase withOpenId(String openId) {
        return null;
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
