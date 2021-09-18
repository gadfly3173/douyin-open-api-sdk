package vip.gadfly.tiktok.open.bean.oauth2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.gadfly.tiktok.open.common.TtOpBaseParam;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TtOpAccessTokenRequest extends TtOpBaseParam {

    public static String GRANT_TYPE_CODE = "authorization_code";
    public static String GRANT_TYPE_REFRESH = "refresh_token";
    public static String GRANT_TYPE_CLIENT = "client_credential";

    /**
     * /oauth/access_token/ 不为空
     */
    private String code;

    @SerializedName("client_key")
    private String clientKey;

    @SerializedName("client_secret")
    private String clientSecret;

    /**
     * /oauth/access_token/  下为  authorization_code
     * /oauth/refresh_token/  下为 refresh_token
     * /oauth/client_token/ 下为 client_credential
     */
    @SerializedName("grant_type")
    private String grantType;

    /**
     * /oauth/refresh_token/ 不为空
     */
    @SerializedName("refresh_token")
    private String refreshToken;

}
