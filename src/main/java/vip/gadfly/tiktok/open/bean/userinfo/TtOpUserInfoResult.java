package vip.gadfly.tiktok.open.bean.userinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

/**
 * https://open.douyin.com/platform/doc/6848806527751489550
 *
 * @author Gadfly
 * @since 2021-09-30 14:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TtOpUserInfoResult extends TtOpBaseResult {
    private static final long serialVersionUID = 1L;

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

    @JSONField(name = "country")
    @JsonAlias("country")
    @JsonProperty("country")
    @SerializedName("country")
    private String country;
    /**
     * 类型: * `EAccountM` - 普通企业号 * `EAccountS` - 认证企业号 * `EAccountK` - 品牌企业号
     */
    @JSONField(name = "e_account_role")
    @JsonAlias("e_account_role")
    @JsonProperty("e_account_role")
    @SerializedName("e_account_role")
    private String eAccountRole;
    /**
     * 性别: * `0` - 未知 * `1` - 男性 * `2` - 女性
     */
    @JSONField(name = "gender")
    @JsonAlias("gender")
    @JsonProperty("gender")
    @SerializedName("gender")
    private Integer gender;

    @JSONField(name = "nickname")
    @JsonAlias("nickname")
    @JsonProperty("nickname")
    @SerializedName("nickname")
    private String nickname;
    /**
     * 用户在当前应用的唯一标识
     */
    @JSONField(name = "open_id")
    @JsonAlias("open_id")
    @JsonProperty("open_id")
    @SerializedName("open_id")
    private String openId;

    @JSONField(name = "province")
    @JsonAlias("province")
    @JsonProperty("province")
    @SerializedName("province")
    private String province;
    /**
     * 用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）
     */
    @JSONField(name = "union_id")
    @JsonAlias("union_id")
    @JsonProperty("union_id")
    @SerializedName("union_id")
    private String unionId;

    @JSONField(name = "encrypt_mobile")
    @JsonAlias("encrypt_mobile")
    @JsonProperty("encrypt_mobile")
    @SerializedName("encrypt_mobile")
    private String encryptMobile;
}
