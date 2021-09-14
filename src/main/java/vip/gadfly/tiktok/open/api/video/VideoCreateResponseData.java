package vip.gadfly.tiktok.open.api.video;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.BaseResult;

public class VideoCreateResponseData extends BaseResult {

    @SerializedName("item_id")
    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;

    }
}
