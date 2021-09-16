package vip.gadfly.tiktok.open.api.video;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseParam;

public class TiktokOpenVideoDataParam extends TiktokOpenBaseParam {

    /**
     * item_id数组，仅能查询access_token对应用户上传的视频
     */
    @SerializedName("item_ids")
    private String[] itemIds;

    public String[] getItemIds() {
        return itemIds;
    }

    public TiktokOpenVideoDataParam setItemIds(String[] itemIds) {
        this.itemIds = itemIds;
        return this;
    }
}

