package vip.gadfly.tiktok.open.api.data.hot;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.BaseResult;

import java.util.List;

public class HotsearchSentencesResult extends BaseResult {

    @SerializedName("list")
    private List<HotSentenceResult> list;

    public List<HotSentenceResult> getList() {
        return list;
    }

    public void setList(List<HotSentenceResult> list) {
        this.list = list;
    }
}
