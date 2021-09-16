package vip.gadfly.tiktok.open.api.video;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;

public class TiktokOpenVideoCreateResponseData extends TiktokOpenBaseResult {

    @SerializedName("item_id")
    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;

    }
}
