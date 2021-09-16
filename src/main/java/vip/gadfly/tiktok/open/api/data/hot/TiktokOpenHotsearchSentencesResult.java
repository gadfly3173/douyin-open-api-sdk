package vip.gadfly.tiktok.open.api.data.hot;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;

import java.util.List;

public class TiktokOpenHotsearchSentencesResult extends TiktokOpenBaseResult {

    @SerializedName("list")
    private List<TiktokOpenHotSentenceResult> list;

    public List<TiktokOpenHotSentenceResult> getList() {
        return list;
    }

    public void setList(List<TiktokOpenHotSentenceResult> list) {
        this.list = list;
    }
}
