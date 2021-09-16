package vip.gadfly.tiktok.open.api.video;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.api.user.TiktokOpenOauthUserInfoResult;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseParam;

import java.util.List;

public class TiktokOpenVideoCreateParam extends TiktokOpenBaseParam {

    /**
     * string
     * example: v0201f510000smhdsr0ggl1v4a2b2ps1
     * video_id, 通过/video/upload/接口得到。注意每次调用/video/create/都要调用/video/upload/生成新的video_id。
     */
    @SerializedName("video_id")
    private String videoId;

    /**
     * string
     * maxLength: 55
     * example: title1#话题1 #话题2 @nickname1
     * 视频标题， 可以带话题,@用户。 如title1#话题1 #话题2 @openid1
     * <p>
     * 注意：话题审核依旧遵循抖音的审核逻辑，强烈建议第三方谨慎拟定话题名称，避免强导流行为。
     */
    private String text;

    /**
     * 地理位置id (未开放)
     */
    @SerializedName("poi_id")
    private String poiId;

    /**
     * 地理位置名称 (未开放)
     */
    @SerializedName("poi_name")
    private String poiName;

    /**
     * string
     * example: ttef9b112671b152ba
     * 小程序id
     */
    @SerializedName("micro_app_id")
    private String microAppId;

    /**
     * string
     * example: 小程序标题
     * 小程序标题
     */
    @SerializedName("micro_app_title")
    private String microAppTitle;

    /**
     * string
     * 吊起小程序时的参数
     */
    @SerializedName("micro_app_url")
    private String microAppUrl;

    /**
     * number($double)
     * example: 2.3
     * 将传入的指定时间点对应帧设置为视频封面（单位：秒）
     */
    @SerializedName("cover_tsp")
    private Double coverTsp;

    /**
     * 如果需要at其他用户。将text中@nickname对应的open_id放到这里
     * string
     * example: 1ad4e099-4a0c-47d1-a410-bffb4f2f64a4
     */
    @SerializedName("at_users")
    private List<TiktokOpenOauthUserInfoResult> atUsers;


    public String getVideoId() {
        return videoId;
    }

    public TiktokOpenVideoCreateParam setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    public String getText() {
        return text;
    }

    public TiktokOpenVideoCreateParam setText(String text) {
        this.text = text;
        return this;
    }

    public String getPoiId() {
        return poiId;
    }

    public TiktokOpenVideoCreateParam setPoiId(String poiId) {
        this.poiId = poiId;
        return this;
    }

    public String getPoiName() {
        return poiName;
    }

    public TiktokOpenVideoCreateParam setPoiName(String poiName) {
        this.poiName = poiName;
        return this;
    }

    public String getMicroAppId() {
        return microAppId;
    }

    public TiktokOpenVideoCreateParam setMicroAppId(String microAppId) {
        this.microAppId = microAppId;
        return this;
    }

    public String getMicroAppTitle() {
        return microAppTitle;
    }

    public TiktokOpenVideoCreateParam setMicroAppTitle(String microAppTitle) {
        this.microAppTitle = microAppTitle;
        return this;
    }

    public String getMicroAppUrl() {
        return microAppUrl;
    }

    public TiktokOpenVideoCreateParam setMicroAppUrl(String microAppUrl) {
        this.microAppUrl = microAppUrl;
        return this;
    }

    public Double getCoverTsp() {
        return coverTsp;
    }

    public TiktokOpenVideoCreateParam setCoverTsp(Double coverTsp) {
        this.coverTsp = coverTsp;
        return this;
    }

    public List<TiktokOpenOauthUserInfoResult> getAtUsers() {
        return atUsers;
    }

    public TiktokOpenVideoCreateParam setAtUsers(List<TiktokOpenOauthUserInfoResult> atUsers) {
        this.atUsers = atUsers;
        return this;
    }
}

