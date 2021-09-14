package vip.gadfly.tiktok.open.api.user;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.BaseResult;

public class FansDataResult extends BaseResult {

    /**
     *
     */
    @SerializedName("fans_data")
    private FansDataDetailResult fansData;

    public FansDataDetailResult getFansData() {
        return fansData;
    }

    public void setFansData(FansDataDetailResult fansData) {
        this.fansData = fansData;
    }
}
