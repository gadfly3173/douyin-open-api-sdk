package vip.gadfly.tiktok.open.api.token;

import vip.gadfly.tiktok.open.common.TtOpBaseParam;

public class TtOpAccessTokenParam extends TtOpBaseParam {

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
    private String grantType = TtOpAccessTokenParam.GRANT_TYPE_CODE;

    /**
     * /oauth/refresh_token/ 不为空
     */
    private String refreshToken;

    public String getCode() {
        return code;
    }

    public TtOpAccessTokenParam setCode(String code) {
        this.code = code;
        return this;
    }

    public String getGrantType() {
        return grantType;
    }

    public TtOpAccessTokenParam setGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public TtOpAccessTokenParam setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
