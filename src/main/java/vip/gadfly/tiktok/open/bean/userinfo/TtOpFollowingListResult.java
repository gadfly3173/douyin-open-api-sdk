package vip.gadfly.tiktok.open.bean.userinfo;

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
 * @since 2021-11-09 10:59
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TtOpFollowingListResult extends TtOpBaseResult {
    private static final long serialVersionUID = 1L;

    @JSONField(name = "list")
    @JsonAlias("list")
    @JsonProperty("list")
    @SerializedName("list")
    private List<TtOpBaseUserInfo> list;
}
