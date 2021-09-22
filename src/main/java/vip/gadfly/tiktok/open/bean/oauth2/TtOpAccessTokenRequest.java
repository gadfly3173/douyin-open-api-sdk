package vip.gadfly.tiktok.open.bean.oauth2;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JSONField(name = "client_key")
    @JsonAlias("client_key")
    @JsonProperty("client_key")
    @SerializedName("client_key")
    private String clientKey;

    @JSONField(name = "client_secret")
    @JsonAlias("client_secret")
    @JsonProperty("client_secret")
    @SerializedName("client_secret")
    private String clientSecret;

    /**
     * /oauth/access_token/  下为  authorization_code
     * /oauth/refresh_token/  下为 refresh_token
     * /oauth/client_token/ 下为 client_credential
     */
    @JSONField(name = "grant_type")
    @JsonAlias("grant_type")
    @JsonProperty("grant_type")
    @SerializedName("grant_type")
    private String grantType;

    /**
     * /oauth/refresh_token/ 不为空
     */
    @JSONField(name = "refresh_token")
    @JsonAlias("refresh_token")
    @JsonProperty("refresh_token")
    @SerializedName("refresh_token")
    private String refreshToken;

}
