package vip.gadfly.tiktok.open.api.oauth;


import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;

/**
 * 网页授权
 *
 * @author hubusi
 * @date 2019/11/10
 */
public class TtOpOauthApi extends AbstractTtOpApiBase {
    public String CONNECTION_URL = getHttpUrl() + "/platform/oauth/connect/";

    /**
     * 自定义 TtOpOauthConnectParam 获取抖音扫一扫登陆URL
     *
     * @param param 自定义 TtOpOauthConnectParam
     * @return
     */
    public String getScanUrl(TtOpOauthConnectParam param) {
        return CONNECTION_URL + "?" + param.getUrlParam();
    }

    /**
     * 根据 redirectUri 获取抖音扫一扫登陆URL
     *
     * @param redirectUri 回调地址
     * @return
     */
    public String getScanUrl(String redirectUri) {
        TtOpOauthConnectParam param = new TtOpOauthConnectParam();
        param.setRedirectUri(redirectUri);
        return CONNECTION_URL + "?" + param.getUrlParam();
    }

    /**
     * 获取抖音扫一扫登陆URL
     *
     * @return
     */
    public String getScanUrl() {
        TtOpOauthConnectParam param = new TtOpOauthConnectParam();
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


    public AbstractTtOpApiBase withAccessToken(String accessToken) {
        return null;
    }

    public AbstractTtOpApiBase withOpenId(String openId) {
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
