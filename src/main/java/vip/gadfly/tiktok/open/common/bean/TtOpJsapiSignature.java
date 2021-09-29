package vip.gadfly.tiktok.open.common.bean;

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

    private String appId;

    private String nonceStr;

    private String timestamp;

    private String url;

    private String signature;

}
