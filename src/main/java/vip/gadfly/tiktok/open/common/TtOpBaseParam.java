package vip.gadfly.tiktok.open.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.http.ITtOpRequest;

import java.io.Serializable;

/**
 * 参数基础实体类
 */
public class TtOpBaseParam implements ITtOpRequest, Serializable {

    @JSONField(name = "open_id")
    @JsonAlias("open_id")
    @JsonProperty("open_id")
    @SerializedName("open_id")
    private String openId;

    @JSONField(name = "access_token")
    @JsonAlias("access_token")
    @JsonProperty("access_token")
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
