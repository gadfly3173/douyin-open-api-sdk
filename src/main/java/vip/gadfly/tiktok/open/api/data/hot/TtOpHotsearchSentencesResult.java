package vip.gadfly.tiktok.open.api.data.hot;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

import java.util.List;

public class TtOpHotsearchSentencesResult extends TtOpBaseResult {

    @SerializedName("list")
    private List<TtOpHotSentenceResult> list;

    public List<TtOpHotSentenceResult> getList() {
        return list;
    }

    public void setList(List<TtOpHotSentenceResult> list) {
        this.list = list;
    }
}
