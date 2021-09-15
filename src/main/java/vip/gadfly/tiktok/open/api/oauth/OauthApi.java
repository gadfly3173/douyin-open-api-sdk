package vip.gadfly.tiktok.open.api.oauth;


import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.base.ApiBase;

/**
 * 网页授权
 *
 * @author hubusi
 * @date 2019/11/10
 */
public class OauthApi extends ApiBase {
    public static String CONNECTION_URL = AppConfig.getInstance().httpUrl + "/platform/oauth/connect/";

    /**
     * 自定义 OauthConnectParam 获取抖音扫一扫登陆URL
     *
     * @param param 自定义 OauthConnectParam
     * @return
     */
    public String getScanUrl(OauthConnectParam param) {
        return CONNECTION_URL + "?" + param.getUrlParam();
    }

    /**
     * 根据 redirectUri 获取抖音扫一扫登陆URL
     *
     * @param redirectUri 回调地址
     * @return
     */
    public String getScanUrl(String redirectUri) {
        OauthConnectParam param = new OauthConnectParam();
        param.setRedirectUri(redirectUri);
        return CONNECTION_URL + "?" + param.getUrlParam();
    }

    /**
     * 获取抖音扫一扫登陆URL
     *
     * @return
     */
    public String getScanUrl() {
        OauthConnectParam param = new OauthConnectParam();
        return CONNECTION_URL + "?" + param.getUrlParam();
    }


    /**
     * 用于获取用户允许授权后，重定向到 redirect_uri 的网址的code值
     *
     * @param scanBackUrl 回调地址
     * @return
     */
    public OauthConnectResult getResult(String scanBackUrl) {
        return JsonUtil.jsonToBean(scanBackUrl, OauthConnectResult.class);
    }


    public ApiBase withAccessToken(String accessToken) {
        return null;
    }

    public ApiBase withOpenId(String openId) {
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
