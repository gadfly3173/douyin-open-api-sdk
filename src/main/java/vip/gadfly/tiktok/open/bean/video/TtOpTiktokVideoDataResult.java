package vip.gadfly.tiktok.open.bean.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

import java.util.List;

/**
 * @author Gadfly
 * @since 2021-11-09 11:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TtOpTiktokVideoDataResult extends TtOpBaseResult {
    private static final long serialVersionUID = 1L;

    @JSONField(name = "list")
    @JsonAlias("list")
    @JsonProperty("list")
    @SerializedName("list")
    private List<Item> list;

    @NoArgsConstructor
    @Data
    public static class Item {
        /**
         * 视频id
         */
        @JSONField(name = "item_id")
        @JsonAlias("item_id")
        @JsonProperty("item_id")
        @SerializedName("item_id")
        private String itemId;
        /**
         * 视频标题
         */
        @JSONField(name = "title")
        @JsonAlias("title")
        @JsonProperty("title")
        @SerializedName("title")
        private String title;
        /**
         * 视频封面
         */
        @JSONField(name = "cover")
        @JsonAlias("cover")
        @JsonProperty("cover")
        @SerializedName("cover")
        private String cover;
        /**
         * 是否置顶
         */
        @JSONField(name = "is_top")
        @JsonAlias("is_top")
        @JsonProperty("is_top")
        @SerializedName("is_top")
        private Boolean isTop;
        /**
         * 视频创建时间戳
         */
        @JSONField(name = "create_time")
        @JsonAlias("create_time")
        @JsonProperty("create_time")
        @SerializedName("create_time")
        private Integer createTime;
        /**
         * 表示是否审核结束。审核通过或者失败都会返回true，审核中返回false。
         */
        @JSONField(name = "is_reviewed")
        @JsonAlias("is_reviewed")
        @JsonProperty("is_reviewed")
        @SerializedName("is_reviewed")
        private Boolean isReviewed;
        /**
         * 表示视频状态。1:细化为5、6、7三种状态;2:不适宜公开;4:审核中;5:公开视频;6:好友可见;7:私密视频
         */
        @JSONField(name = "video_status")
        @JsonAlias("video_status")
        @JsonProperty("video_status")
        @SerializedName("video_status")
        private Integer videoStatus;
        /**
         * 视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页。
         */
        @JSONField(name = "share_url")
        @JsonAlias("share_url")
        @JsonProperty("share_url")
        @SerializedName("share_url")
        private String shareUrl;
        /**
         * 统计数据
         */
        @JSONField(name = "statistics")
        @JsonAlias("statistics")
        @JsonProperty("statistics")
        @SerializedName("statistics")
        private Statistics statistics;
        /**
         * 媒体类型。2:图集;4:视频
         */
        @JSONField(name = "media_type")
        @JsonAlias("media_type")
        @JsonProperty("media_type")
        @SerializedName("media_type")
        private Integer mediaType;

        @NoArgsConstructor
        @Data
        public static class Statistics {
            /**
             * 评论数
             */
            @JSONField(name = "comment_count")
            @JsonAlias("comment_count")
            @JsonProperty("comment_count")
            @SerializedName("comment_count")
            private Integer commentCount;
            /**
             * 点赞数
             */
            @JSONField(name = "digg_count")
            @JsonAlias("digg_count")
            @JsonProperty("digg_count")
            @SerializedName("digg_count")
            private Integer diggCount;
            /**
             * 下载数
             */
            @JSONField(name = "download_count")
            @JsonAlias("download_count")
            @JsonProperty("download_count")
            @SerializedName("download_count")
            private Integer downloadCount;
            /**
             * 播放数，只有作者本人可见。公开视频设为私密后，播放数也会返回0。
             */
            @JSONField(name = "play_count")
            @JsonAlias("play_count")
            @JsonProperty("play_count")
            @SerializedName("play_count")
            private Integer playCount;
            /**
             * 分享数
             */
            @JSONField(name = "share_count")
            @JsonAlias("share_count")
            @JsonProperty("share_count")
            @SerializedName("share_count")
            private Integer shareCount;
            /**
             * 转发数
             */
            @JSONField(name = "forward_count")
            @JsonAlias("forward_count")
            @JsonProperty("forward_count")
            @SerializedName("forward_count")
            private Integer forwardCount;
        }
    }
}
