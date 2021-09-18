package vip.gadfly.tiktok.open.base.entity;

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
public class TiktokOpenJsapiSignature implements Serializable {
    private static final long serialVersionUID = -1116808193154384804L;

    private String appId;

    private String nonceStr;

    private long timestamp;

    private String url;

    private String signature;

}
