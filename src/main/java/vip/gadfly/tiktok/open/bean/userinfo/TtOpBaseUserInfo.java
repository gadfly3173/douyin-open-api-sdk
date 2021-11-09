package vip.gadfly.tiktok.open.bean.userinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Gadfly
 * @since 2021-11-09 12:10
 */
@Data
public class TtOpBaseUserInfo {
    /**
     * 用户在当前应用的唯一标识
     */
    @JSONField(name = "open_id")
    @JsonAlias("open_id")
    @JsonProperty("open_id")
    @SerializedName("open_id")
    private String openId;
    /**
     * 用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）
     */
    @JSONField(name = "union_id")
    @JsonAlias("union_id")
    @JsonProperty("union_id")
    @SerializedName("union_id")
    private String unionId;

    @JSONField(name = "nickname")
    @JsonAlias("nickname")
    @JsonProperty("nickname")
    @SerializedName("nickname")
    private String nickname;

    @JSONField(name = "avatar")
    @JsonAlias("avatar")
    @JsonProperty("avatar")
    @SerializedName("avatar")
    private String avatar;

    @JSONField(name = "city")
    @JsonAlias("city")
    @JsonProperty("city")
    @SerializedName("city")
    private String city;

    @JSONField(name = "province")
    @JsonAlias("province")
    @JsonProperty("province")
    @SerializedName("province")
    private String province;

    @JSONField(name = "country")
    @JsonAlias("country")
    @JsonProperty("country")
    @SerializedName("country")
    private String country;
    /**
     * 性别: `0` - 未知， `1` - 男性， `2` - 女性
     */
    @JSONField(name = "gender")
    @JsonAlias("gender")
    @JsonProperty("gender")
    @SerializedName("gender")
    private Integer gender;
}
