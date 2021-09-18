package vip.gadfly.tiktok.open.api.data.hot;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

public class TtOpHotSentenceResult extends TtOpBaseResult {

    @SerializedName("hot_level")
    private Integer hotLevel;

    private String sentence;

    /**
     * 标签: `0` - 无 `1` - 新 `2` - 推荐 `3` - 热 `4` - 爆 `5` - 首发
     */
    private String label;

    public Integer getHotLevel() {
        return hotLevel;
    }

    public void setHotLevel(Integer hotLevel) {
        this.hotLevel = hotLevel;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}