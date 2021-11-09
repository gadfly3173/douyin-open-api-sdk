package vip.gadfly.tiktok.open.bean.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.List;

/**
 * https://open.douyin.com/platform/doc/6848798087398295555
 *
 * @author Gadfly
 * @since 2021-09-30 10:32
 */
@Data
@Accessors(chain = true)
public class TtOpTiktokVideoDataRequest {
    /**
     * item_id数组，仅能查询access_token对应用户上传的视频
     */
    @JSONField(name = "item_ids")
    @JsonAlias("item_ids")
    @JsonProperty("item_ids")
    @SerializedName("item_ids")
    private List<String> itemIds;
}
