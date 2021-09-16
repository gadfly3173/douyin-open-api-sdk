package vip.gadfly.tiktok.open.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TiktokOpenBaseResult implements Serializable {

    @SerializedName("error_code")
    private int errCode;

    private String description;

    private Long cursor;

    @SerializedName("has_more")
    private boolean hasMore;

    private Integer total;


    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
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
