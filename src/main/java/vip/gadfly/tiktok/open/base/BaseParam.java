package vip.gadfly.tiktok.open.base;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.core.utils.StringUtil;

import java.io.Serializable;

/**
 * 获取授权码(code) 参数实体类
 */
public class BaseParam implements Serializable {

    /**
     * 应用唯一标识
     */
    private String appId = AppConfig.getInstance().appId;

    /**
     * 密钥
     */
    private String appSecret = AppConfig.getInstance().appSecret;

    @SerializedName("open_id")
    private String openId;

    @SerializedName("access_token")
    private String accessToken;

    private Long cursor = 0L;

    private Long count = 20L;

    public String getAppId() {
        return appId;
    }

    public BaseParam setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public BaseParam setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public BaseParam setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public BaseParam setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Long getCursor() {
        return cursor;
    }

    public BaseParam setCursor(Long cursor) {
        this.cursor = cursor;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public BaseParam setCount(Long count) {
        this.count = count;
        return this;
    }

    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getUrlParam() {
        String params = "access_token=" + this.getAccessToken();
        if (!StringUtil.isEmpty(this.getOpenId())) {
            params = params + "&open_id=" + this.getOpenId();
        }
        if (!StringUtil.isEmpty(this.getCursor())) {
            params = params + "&cursor=" + this.getCursor() + "&count=" + this.getCount();
        }
        return params;
    }

    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getNoPageUrlParam() {
        String params = "access_token=" + this.getAccessToken();
        if (!StringUtil.isEmpty(this.getOpenId())) {
            params = params + "&open_id=" + this.getOpenId();
        }
        return params;
    }
}
