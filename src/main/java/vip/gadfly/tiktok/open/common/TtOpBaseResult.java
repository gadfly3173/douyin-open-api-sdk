package vip.gadfly.tiktok.open.common;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.http.ITtOpResponse;

public class TtOpBaseResult implements ITtOpResponse {

    @SerializedName("error_code")
    private int errorCode;

    private String description;

    private Long cursor;

    @SerializedName("has_more")
    private boolean hasMore;

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
