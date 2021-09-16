package vip.gadfly.tiktok.open.api.user;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;

public class TiktokOpenFansDataResult extends TiktokOpenBaseResult {

    /**
     *
     */
    @SerializedName("fans_data")
    private TiktokOpenFansDataDetailResult fansData;

    public TiktokOpenFansDataDetailResult getFansData() {
        return fansData;
    }

    public void setFansData(TiktokOpenFansDataDetailResult fansData) {
        this.fansData = fansData;
    }
}
