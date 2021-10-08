package vip.gadfly.tiktok.open.bean.oauth2;

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

    private String clientKey;

    private String nonceStr;

    private String timestamp;

    private String url;

    private String signature;

}
