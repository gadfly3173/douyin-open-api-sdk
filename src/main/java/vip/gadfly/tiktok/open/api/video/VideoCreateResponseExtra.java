package vip.gadfly.tiktok.open.api.video;

import com.google.gson.annotations.SerializedName;

public class VideoCreateResponseExtra {

    @SerializedName("log_id")
    private String logId;

    private Long now;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Long getNow() {
        return now;
    }

    public void setNow(Long now) {
        this.now = now;
    }
}
