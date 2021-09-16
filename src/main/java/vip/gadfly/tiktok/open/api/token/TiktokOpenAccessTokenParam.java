package vip.gadfly.tiktok.open.api.token;

import vip.gadfly.tiktok.open.base.TiktokOpenBaseParam;

public class TiktokOpenAccessTokenParam extends TiktokOpenBaseParam {

    public static String GRANT_TYPE_CODE = "authorization_code";
    public static String GRANT_TYPE_REFRESH = "refresh_token";
    public static String GRANT_TYPE_CLIENT = "client_credential";

    /**
     * /oauth/access_token/ 不为空
     */
    private String code;

    /**
     * /oauth/access_token/  下为  authorization_code
     * /oauth/refresh_token/  下为 refresh_token
     * /oauth/client_token/ 下为 client_credential
     */
    private String grantType = TiktokOpenAccessTokenParam.GRANT_TYPE_CODE;

    /**
     * /oauth/refresh_token/ 不为空
     */
    private String refreshToken;

    public String getCode() {
        return code;
    }

    public TiktokOpenAccessTokenParam setCode(String code) {
        this.code = code;
        return this;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getUrlParam() {
        return "client_key=" + this.getAppId() + "&client_secret=" + this.getAppSecret() +
                       "&code=" + this.code + "&grant_type=" + this.grantType;
    }

    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getClientUrlParam() {
        return "client_key=" + this.getAppId() + "&client_secret=" + this.getAppSecret() +
                       "&grant_type=" + this.grantType;
    }

    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getRefreshUrlParam() {
        return "client_key=" + this.getAppId() + "&refresh_token=" + this.getRefreshToken() +
                       "&grant_type=" + this.grantType;
    }
}
