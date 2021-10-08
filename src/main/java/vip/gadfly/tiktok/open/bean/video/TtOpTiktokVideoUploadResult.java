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
 * @since 2021-09-30 12:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TtOpTiktokVideoUploadResult extends TtOpBaseResult {
    @JSONField(name = "video")
    @JsonAlias("video")
    @JsonProperty("video")
    @SerializedName("video")
    private Video video;

    @Data
    public static class Video {
        @JSONField(name = "height")
        @JsonAlias("height")
        @JsonProperty("height")
        @SerializedName("height")
        private Long height;
        /**
         * 视频文件id。
         */
        @JSONField(name = "video_id")
        @JsonAlias("video_id")
        @JsonProperty("video_id")
        @SerializedName("video_id")
        private String videoId;

        @JSONField(name = "width")
        @JsonAlias("width")
        @JsonProperty("width")
        @SerializedName("width")
        private Long width;
    }
}
