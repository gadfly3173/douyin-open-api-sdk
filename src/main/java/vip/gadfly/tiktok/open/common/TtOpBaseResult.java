package vip.gadfly.tiktok.open.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.http.ITtOpResponse;

public class TtOpBaseResult implements ITtOpResponse {

    @JSONField(name = "error_code")
    @JsonAlias("error_code")
    @JsonProperty("error_code")
    @SerializedName("error_code")
    private int errorCode;

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


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCursor() {
        return cursor;
    }

    public void setCursor(Long cursor) {
        this.cursor = cursor;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
