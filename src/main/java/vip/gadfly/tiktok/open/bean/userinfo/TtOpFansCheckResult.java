package vip.gadfly.tiktok.open.bean.userinfo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

/**
 * @author Gadfly
 * @since 2021-11-09 11:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TtOpFansCheckResult extends TtOpBaseResult {
    private static final long serialVersionUID = 1L;

    /**
     * is_follower，为避免JSON解析错误，增加前缀bool
     * follower_open_id是否关注了open_id
     */
    @JSONField(name = "is_follower")
    @JsonAlias("is_follower")
    @JsonProperty("is_follower")
    @SerializedName("is_follower")
    private Boolean boolIsFollower;
    /**
     * 若关注了，则返回关注时间。单位为秒级时间戳
     */
    @JSONField(name = "follow_time")
    @JsonAlias("follow_time")
    @JsonProperty("follow_time")
    @SerializedName("follow_time")
    private Long followTime;
}
