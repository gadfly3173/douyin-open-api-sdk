package vip.gadfly.tiktok.open.api.token;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;


public class TiktokOpenAccessTokenResult extends TiktokOpenBaseResult {

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
    private Integer expiresIn;

    private Long expiredTime = 0L;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("ticket")
    private String ticket;

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

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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
        Integer expiresIn = this.getExpiresIn();
        long expiredTime = 0L;
        if (expiresIn != null)
            // 过期时间加两小时 减10秒是为了网络误差 *1000 变成毫秒
            expiredTime = (System.currentTimeMillis() + ((expiresIn - TiktokOpenAccessTokenConfig.OutTime) * 1000));
        this.setExpiredTime(expiredTime);
        TiktokOpenAccessTokenConfig.getAccessTokenCache().set(cacheKey, JsonUtil.objectToJson(this), expiresIn);
    }
}
