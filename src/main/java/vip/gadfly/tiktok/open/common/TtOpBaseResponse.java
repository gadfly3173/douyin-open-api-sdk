package vip.gadfly.tiktok.open.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import vip.gadfly.tiktok.open.common.bean.TtOpBaseResponseBase;

/**
 * 抖音开放平台返回结果
 *
 * @author Gadfly
 * @since 2021-09-24 15:15
 */
@Data
public class TtOpBaseResponse<T> {
    /**
     * 业务参数
     */
    @JSONField(name = "data")
    @JsonAlias("data")
    @JsonProperty("data")
    @SerializedName("data")
    private T data;

    /**
     * 企业号结果公共参数
     */
    @JSONField(name = "base")
    @JsonAlias("base")
    @JsonProperty("base")
    @SerializedName("base")
    private TtOpBaseResponseBase base;

    /**
     * 消息
     */
    @JSONField(name = "message")
    @JsonAlias("message")
    @JsonProperty("message")
    @SerializedName("message")
    private String message;
}
