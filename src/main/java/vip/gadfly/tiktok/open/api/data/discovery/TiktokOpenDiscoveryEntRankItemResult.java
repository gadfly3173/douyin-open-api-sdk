package vip.gadfly.tiktok.open.api.data.discovery;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;

import java.util.List;

public class TiktokOpenDiscoveryEntRankItemResult extends TiktokOpenBaseResult {

    @SerializedName("active_time")
    private String activeTime;

    private List<TiktokOpenEntRankItem> tiktokOpenEntRankItemList;


    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public List<TiktokOpenEntRankItem> getEntRankItemList() {
        return tiktokOpenEntRankItemList;
    }

    public void setEntRankItemList(List<TiktokOpenEntRankItem> tiktokOpenEntRankItemList) {
        this.tiktokOpenEntRankItemList = tiktokOpenEntRankItemList;
    }
}
