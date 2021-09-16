package vip.gadfly.tiktok.open.api.video;

import com.google.gson.annotations.SerializedName;

public class TiktokOpenVideoCreateResponse {

    @SerializedName("data")
    private TiktokOpenVideoCreateResponseData data;

    private TiktokOpenVideoCreateResponseExtra extra;


    public TiktokOpenVideoCreateResponseData getData() {
        return data;
    }

    public void setData(TiktokOpenVideoCreateResponseData data) {
        this.data = data;
    }

    public TiktokOpenVideoCreateResponseExtra getExtra() {
        return extra;
    }

    public void setExtra(TiktokOpenVideoCreateResponseExtra extra) {
        this.extra = extra;
    }
}

