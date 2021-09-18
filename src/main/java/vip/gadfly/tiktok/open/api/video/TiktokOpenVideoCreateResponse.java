package vip.gadfly.tiktok.open.api.video;

import com.google.gson.annotations.SerializedName;

public class TiktokOpenVideoCreateResponse {

    @SerializedName("data")
    private TtOpVideoCreateResponseData data;

    private TiktokOpenVideoCreateResponseExtra extra;


    public TtOpVideoCreateResponseData getData() {
        return data;
    }

    public void setData(TtOpVideoCreateResponseData data) {
        this.data = data;
    }

    public TiktokOpenVideoCreateResponseExtra getExtra() {
        return extra;
    }

    public void setExtra(TiktokOpenVideoCreateResponseExtra extra) {
        this.extra = extra;
    }
}

