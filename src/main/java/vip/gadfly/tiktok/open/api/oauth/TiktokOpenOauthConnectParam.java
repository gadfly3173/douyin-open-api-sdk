package vip.gadfly.tiktok.open.api.oauth;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseParam;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * 获取授权码(code) 参数实体类
 */
public class TiktokOpenOauthConnectParam extends TiktokOpenBaseParam {
    /**
     * 填写code
     */
    private String responseType = "code";

    /**
     * 应用授权作用域,多个授权作用域以英文逗号（,）分隔
     */
    private String scope = String.join(",", DouyinConf.scopeAllList);

    /**
     * 授权成功后的回调地址，必须以http/https开头
     */
    private String redirectUri = AppConfig.getInstance().redirectUri;


    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getUrlParam() {
        return "client_key=" + this.getAppId() + "&response_type=" + this.responseType +
                       "&scope=" + this.scope + "&state=dy" + "&redirect_uri=" + this.getRedirectUri();
    }

    public String getResponseType() {
        return responseType;
    }

    public TiktokOpenOauthConnectParam setResponseType(String responseType) {
        this.responseType = responseType;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public TiktokOpenOauthConnectParam setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public TiktokOpenOauthConnectParam setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }
}
