package vip.gadfly.tiktok.open.base.entity.video;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.http.ITiktokOpenRequest;

import java.io.Serializable;

public class TiktokOpenVideoStatisticsBase implements ITiktokOpenRequest, Serializable {


    /**
     * integer($int32)
     * example: 100
     * 评论数
     */
    @SerializedName("comment_count")
    private Long commentCount;

    /**
     * integer($int32)
     * example: 200
     * 点赞数
     */
    @SerializedName("digg_count")
    private Long diggCount;

    /**
     * integer($int32)
     * example: 10
     * 下载数
     */
    @SerializedName("download_count")
    private Long downloadCount;

    /**
     * integer($int32)
     * example: 300
     * 播放数
     */
    @SerializedName("play_count")
    private Long playCount;

    /**
     * integer($int32)
     * example: 10
     * 分享数
     */
    @SerializedName("share_count")
    private Long shareCount;

    /**
     * integer($int32)
     * example: 10
     * 转发数
     */
    @SerializedName("forward_count")
    private Long forwardCount;


    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getDiggCount() {
        return diggCount;
    }

    public void setDiggCount(Long diggCount) {
        this.diggCount = diggCount;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

    public Long getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(Long forwardCount) {
        this.forwardCount = forwardCount;
    }
}
