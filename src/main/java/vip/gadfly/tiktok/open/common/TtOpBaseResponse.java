package vip.gadfly.tiktok.open.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 抖音开放平台返回结果
 *
 * @author Gadfly
 * @since 2021-09-24 15:15
 */
@NoArgsConstructor
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
    @JSONField(name = "extra")
    @JsonAlias("extra")
    @JsonProperty("extra")
    @SerializedName("extra")
    private TtOpBaseResponseExtra extra;

    @Data
    public static class TtOpBaseResponseBase {
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

    @Data
    public static class TtOpBaseResponseExtra {
        @JSONField(name = "description")
        @JsonAlias("description")
        @JsonProperty("description")
        @SerializedName("description")
        private String description;

        @JSONField(name = "error_code")
        @JsonAlias("error_code")
        @JsonProperty("error_code")
        @SerializedName("error_code")
        private Integer errorCode;

        @JSONField(name = "logid")
        @JsonAlias("logid")
        @JsonProperty("logid")
        @SerializedName("logid")
        private String logid;

        @JSONField(name = "now")
        @JsonAlias("now")
        @JsonProperty("now")
        @SerializedName("now")
        private Integer now;

        @JSONField(name = "sub_description")
        @JsonAlias("sub_description")
        @JsonProperty("sub_description")
        @SerializedName("sub_description")
        private String subDescription;

        @JSONField(name = "sub_error_code")
        @JsonAlias("sub_error_code")
        @JsonProperty("sub_error_code")
        @SerializedName("sub_error_code")
        private Integer subErrorCode;
    }
}
