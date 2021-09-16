package vip.gadfly.tiktok.open.api.token;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.base.BaseResult;


public class AccessTokenResult extends BaseResult {

    /**
     * "error_code": 0,
     * "description": "",
     * "access_token": "access_token",
     * "expires_in": 86400,
     * "refresh_token": "refresh_token",
     * "open_id": "aaa-bbb-ccc",
     * "scope": "user_info",
     * "unionid": "ccc-ddd-eee-fff"
     */

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private Long expiresIn;

    private Long expiredTime = 0L;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("open_id")
    private String openId;

    private String scope;

    private String unionid;

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 存缓存
     *
     * @param cacheKey
     */
    public void saveCache(String cacheKey) {
        Long expiresIn = this.getExpiresIn();
        Long expiredTime = 0L;
        if (expiresIn != null)
            // 过期时间加两小时 减10秒是为了网络误差 *1000 变成毫秒
            expiredTime = (System.currentTimeMillis() + ((expiresIn - AccessTokenConfig.OutTime) * 1000));
        this.setExpiredTime(expiredTime);
        AccessTokenConfig.getInstance().getAccessTokenCache().set(cacheKey, JsonUtil.objectToJson(this), expiresIn);
    }


}
