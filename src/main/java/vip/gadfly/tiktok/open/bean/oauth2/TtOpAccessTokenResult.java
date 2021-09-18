package vip.gadfly.tiktok.open.bean.oauth2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

/**
 * @author Gadfly
 * @since 2021-09-18 15:23
 */
@Data
public class TtOpAccessTokenResult extends TtOpBaseResult {

    private static final long serialVersionUID = 1L;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private int expiresIn = -1;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("refresh_expires_in")
    private int refreshExpiresIn = -1;

    @SerializedName("ticket")
    private String ticket;

    @SerializedName("open_id")
    private String openId;

    private String scope;

}
