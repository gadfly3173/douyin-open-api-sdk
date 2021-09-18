package vip.gadfly.tiktok.open.common;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.http.ITtOpRequest;

import java.io.Serializable;

/**
 * 参数基础实体类
 */
public class TtOpBaseParam implements ITtOpRequest, Serializable {

    @SerializedName("open_id")
    private String openId;

    @SerializedName("access_token")
    private String accessToken;

    public String getOpenId() {
        return openId;
    }

    public TtOpBaseParam setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public TtOpBaseParam setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
