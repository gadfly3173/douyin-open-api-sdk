package vip.gadfly.tiktok.open.api.oauth;

import java.io.Serializable;

/**
 * 获取授权码(code) 参数实体类
 */
public class OauthConnectResult implements Serializable {

    /**
     * 回调 code
     */
    private String code;

    /**
     * 回调 state
     */
    private String state;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
