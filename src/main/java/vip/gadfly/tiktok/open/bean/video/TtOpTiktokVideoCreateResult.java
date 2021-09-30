package vip.gadfly.tiktok.open.bean.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

/**
 * @author Gadfly
 * @since 2021-09-30 12:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TtOpTiktokVideoCreateResult extends TtOpBaseResult {
    /**
     * 抖音视频id
     */
    @JSONField(name = "item_id")
    @JsonAlias("item_id")
    @JsonProperty("item_id")
    @SerializedName("item_id")
    private String itemId;
}
