package vip.gadfly.tiktok.open.api.video;

import com.google.gson.annotations.SerializedName;

public class VideoCreateResponse {

    @SerializedName("data")
    private VideoCreateResponseData data;

    private VideoCreateResponseExtra extra;


    public VideoCreateResponseData getData() {
        return data;
    }

    public void setData(VideoCreateResponseData data) {
        this.data = data;
    }

    public VideoCreateResponseExtra getExtra() {
        return extra;
    }

    public void setExtra(VideoCreateResponseExtra extra) {
        this.extra = extra;
    }
}

