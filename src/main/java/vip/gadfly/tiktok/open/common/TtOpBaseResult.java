package vip.gadfly.tiktok.open.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import vip.gadfly.tiktok.core.http.ITtOpResponse;

@Data
public class TtOpBaseResult implements ITtOpResponse {

    /**
     * 错误码
     */
    @JSONField(name = "error_code")
    @JsonAlias("error_code")
    @JsonProperty("error_code")
    @SerializedName("error_code")
    private Integer errorCode;

    /**
     * 错误码描述
     */
    @JSONField(name = "description")
    @JsonAlias("description")
    @JsonProperty("description")
    @SerializedName("description")
    private String description;

    @JSONField(name = "cursor")
    @JsonAlias("cursor")
    @JsonProperty("cursor")
    @SerializedName("cursor")
    private Long cursor;

    @JSONField(name = "has_more")
    @JsonAlias("has_more")
    @JsonProperty("has_more")
    @SerializedName("has_more")
    private boolean hasMore;

    @JSONField(name = "total")
    @JsonAlias("total")
    @JsonProperty("total")
    @SerializedName("total")
    private Integer total;
}
