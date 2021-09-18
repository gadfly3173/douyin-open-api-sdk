package vip.gadfly.tiktok.open.common.bean.video;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.http.ITtOpRequest;

import java.io.Serializable;

public class TtOpBaseVideo implements ITtOpRequest, Serializable {

    /**
     * string
     * example: v0201f510000smhdsr0ggl1v4a2b2ps1
     * video_id, 通过/video/upload/接口得到。注意每次调用/video/create/都要调用/video/upload/生成新的video_id。
     */
    @SerializedName("video_id")
    private String videoId;

    /**
     * string
     */
    @SerializedName("item_id")
    private String itemId;

    /**
     * string
     * example: 测试视频 #测试话题 @抖音小助手
     * 视频标题
     */
    private String title;

    /**
     * string
     * example: https://p3-dy.byteimg.com/img/tos-cn-p-0015/cfa0d6421bdc4580876cb16974539ff6~c5_300x400.jpeg
     * 视频封面
     */
    private String cover;

    /**
     * boolean
     * example: false
     * 是否置顶
     */
    @SerializedName("is_top")
    private String isTop;

    /**
     * integer($int64)
     * example: 1571075129
     * 视频创建时间戳
     */
    @SerializedName("create_time")
    private Long createTime;

    /**
     * boolean
     * example: true
     * 是否审核通过
     */
    @SerializedName("is_reviewed")
    private String isReviewed;

    /**
     * string
     * example: https://www.iesdouyin.com/share/video/QDlWd0EzdWVMU2Q0aU5tKzVaOElvVU03ODBtRHFQUCtLUHBSMHFRT21MVkFYYi9UMDYwemRSbVlxaWczNTd6RUJRc3MrM2hvRGlqK2EwNnhBc1lGUkpRPT0=/?region=CN&mid=6753173704399670023&u_code=12h9je425&titleType=title
     * 视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页。
     */
    @SerializedName("share_url")
    private String shareUrl;

    /**
     * 视频统计
     */
    private TtOpVideoStatisticsBase statistics;


    public String getVideoId() {
        return videoId;
    }

    public TtOpBaseVideo setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TtOpBaseVideo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCover() {
        return cover;
    }

    public TtOpBaseVideo setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public String getIsTop() {
        return isTop;
    }

    public TtOpBaseVideo setIsTop(String isTop) {
        this.isTop = isTop;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public TtOpBaseVideo setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getIsReviewed() {
        return isReviewed;
    }

    public TtOpBaseVideo setIsReviewed(String isReviewed) {
        this.isReviewed = isReviewed;
        return this;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public TtOpBaseVideo setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
        return this;
    }

    public TtOpVideoStatisticsBase getStatistics() {
        return statistics;
    }

    public TtOpBaseVideo setStatistics(TtOpVideoStatisticsBase statistics) {
        this.statistics = statistics;
        return this;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}