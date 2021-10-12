package vip.gadfly.tiktok.open.bean.oauth2;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * jspai signature.
 *
 * @author Daniel Qian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TtOpJsapiSignature implements Serializable {
    private static final long serialVersionUID = -1116808193154384804L;

    @JSONField(name = "client_key")
    @JsonAlias("client_key")
    @JsonProperty("client_key")
    @SerializedName("client_key")
    private String clientKey;

    @JSONField(name = "nonce_str")
    @JsonAlias("nonce_str")
    @JsonProperty("nonce_str")
    @SerializedName("nonce_str")
    private String nonceStr;

    @JSONField(name = "timestamp")
    @JsonAlias("timestamp")
    @JsonProperty("timestamp")
    @SerializedName("timestamp")
    private String timestamp;

    @JSONField(name = "url")
    @JsonAlias("url")
    @JsonProperty("url")
    @SerializedName("url")
    private String url;

    @JSONField(name = "signature")
    @JsonAlias("signature")
    @JsonProperty("signature")
    @SerializedName("signature")
    private String signature;

}
