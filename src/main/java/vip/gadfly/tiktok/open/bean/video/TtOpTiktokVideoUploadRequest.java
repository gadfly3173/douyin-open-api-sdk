package vip.gadfly.tiktok.open.bean.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;

/**
 * https://open.douyin.com/platform/doc/6848798087398295555
 *
 * @author Gadfly
 * @since 2021-09-30 10:32
 */
@Data
@Accessors(chain = true)
public class TtOpTiktokVideoUploadRequest {
    /**
     * 视频文件
     */
    @JSONField(name = "video")
    @JsonAlias("video")
    @JsonProperty("video")
    @SerializedName("video")
    private File video;
}
