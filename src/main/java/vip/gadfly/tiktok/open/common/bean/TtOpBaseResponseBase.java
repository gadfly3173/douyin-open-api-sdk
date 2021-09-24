package vip.gadfly.tiktok.open.common.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 企业号请求结果公共参数
 *
 * @author Gadfly
 * @since 2021-09-24 15:17
 */
@Data
public class TtOpBaseResponseBase {
    /**
     * 请求日志ID
     */
    @JSONField(name = "log_id")
    @JsonAlias("log_id")
    @JsonProperty("log_id")
    @SerializedName("log_id")
    private String log_id;
    /**
     * 网关状态码
     */
    @JSONField(name = "gateway_code")
    @JsonAlias("gateway_code")
    @JsonProperty("gateway_code")
    @SerializedName("gateway_code")
    private Integer gateway_code;
    /**
     * 网关状态信息
     */
    @JSONField(name = "gateway_msg")
    @JsonAlias("gateway_msg")
    @JsonProperty("gateway_msg")
    @SerializedName("gateway_msg")
    private String gateway_msg;
    /**
     * 业务状态码
     */
    @JSONField(name = "biz_code")
    @JsonAlias("biz_code")
    @JsonProperty("biz_code")
    @SerializedName("biz_code")
    private Integer biz_code;
    /**
     * 业务状态信息
     */
    @JSONField(name = "biz_msg")
    @JsonAlias("biz_msg")
    @JsonProperty("biz_msg")
    @SerializedName("biz_msg")
    private String biz_msg;
}
