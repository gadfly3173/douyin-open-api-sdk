package vip.gadfly.tiktok.open.bean.video;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * https://open.douyin.com/platform/doc/6848798087398328323
 *
 * @author Gadfly
 * @since 2021-09-30 10:32
 */
@Data
@Accessors(chain = true)
public class TtOpTiktokVideoCreateRequest {
    /**
     * video_id, 通过/video/upload/接口得到。注意每次调用/video/create/都要调用/video/upload/生成新的video_id。
     */
    @JSONField(name = "video_id")
    @JsonAlias("video_id")
    @JsonProperty("video_id")
    @SerializedName("video_id")
    private String videoId;
    /**
     * 视频标题， 可以带话题,@用户。注意：话题审核依旧遵循抖音的审核逻辑，强烈建议第三方谨慎拟定话题名称，避免强导流行为。
     */
    @JSONField(name = "text")
    @JsonAlias("text")
    @JsonProperty("text")
    @SerializedName("text")
    private String text;
    /**
     * 地理位置id，poi_id可通过"查询POI信息"能力获取
     */
    @JSONField(name = "poi_id")
    @JsonAlias("poi_id")
    @JsonProperty("poi_id")
    @SerializedName("poi_id")
    private String poiId;
    /**
     * 地理位置名称
     */
    @JSONField(name = "poi_name")
    @JsonAlias("poi_name")
    @JsonProperty("poi_name")
    @SerializedName("poi_name")
    private String poiName;
    /**
     * 小程序id
     */
    @JSONField(name = "micro_app_id")
    @JsonAlias("micro_app_id")
    @JsonProperty("micro_app_id")
    @SerializedName("micro_app_id")
    private String microAppId;
    /**
     * 小程序标题
     */
    @JSONField(name = "micro_app_title")
    @JsonAlias("micro_app_title")
    @JsonProperty("micro_app_title")
    @SerializedName("micro_app_title")
    private String microAppTitle;
    /**
     * 将传入的指定时间点对应帧设置为视频封面（单位：秒）
     */
    @JSONField(name = "cover_tsp")
    @JsonAlias("cover_tsp")
    @JsonProperty("cover_tsp")
    @SerializedName("cover_tsp")
    private Double coverTsp;
    /**
     * 如果需要at其他用户。将text中@nickname对应的open_id放到这里。
     */
    @JSONField(name = "at_users")
    @JsonAlias("at_users")
    @JsonProperty("at_users")
    @SerializedName("at_users")
    private List<String> atUsers;
    /**
     * 开发者在小程序中生成该页面时写的path地址
     */
    @JSONField(name = "micro_app_url")
    @JsonAlias("micro_app_url")
    @JsonProperty("micro_app_url")
    @SerializedName("micro_app_url")
    private String microAppUrl;
    /**
     * 自定义封面图片,参数为接口/image/upload/ 返回的image_id
     */
    @JSONField(name = "custom_cover_image_url")
    @JsonAlias("custom_cover_image_url")
    @JsonProperty("custom_cover_image_url")
    @SerializedName("custom_cover_image_url")
    private String customCoverImageUrl;
}
