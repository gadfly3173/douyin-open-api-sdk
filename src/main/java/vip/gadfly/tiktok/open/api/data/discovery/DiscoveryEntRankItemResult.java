package vip.gadfly.tiktok.open.api.data.discovery;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.BaseResult;

import java.util.List;

public class DiscoveryEntRankItemResult extends BaseResult {

    @SerializedName("active_time")
    private String activeTime;

    private List<EntRankItem> entRankItemList;


    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public List<EntRankItem> getEntRankItemList() {
        return entRankItemList;
    }

    public void setEntRankItemList(List<EntRankItem> entRankItemList) {
        this.entRankItemList = entRankItemList;
    }
}
