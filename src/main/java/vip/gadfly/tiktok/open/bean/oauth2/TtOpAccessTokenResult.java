package vip.gadfly.tiktok.open.bean.oauth2;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

/**
 * @author Gadfly
 * @since 2021-09-18 15:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TtOpAccessTokenResult extends TtOpBaseResult {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "access_token")
    @JsonAlias("access_token")
    @JsonProperty("access_token")
    @SerializedName("access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    @JsonAlias("expires_in")
    @JsonProperty("expires_in")
    @SerializedName("expires_in")
    private int expiresIn = -1;

    @JSONField(name = "refresh_token")
    @JsonAlias("refresh_token")
    @JsonProperty("refresh_token")
    @SerializedName("refresh_token")
    private String refreshToken;

    @JSONField(name = "refresh_expires_in")
    @JsonAlias("refresh_expires_in")
    @JsonProperty("refresh_expires_in")
    @SerializedName("refresh_expires_in")
    private int refreshExpiresIn = -1;

    @JSONField(name = "ticket")
    @JsonAlias("ticket")
    @JsonProperty("ticket")
    @SerializedName("ticket")
    private String ticket;

    @JSONField(name = "open_id")
    @JsonAlias("open_id")
    @JsonProperty("open_id")
    @SerializedName("open_id")
    private String openId;

    @JSONField(name = "scope")
    @JsonAlias("scope")
    @JsonProperty("scope")
    @SerializedName("scope")
    private String scope;

}
