package vip.gadfly.tiktok.open.api.token;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vip.gadfly.tiktok.core.exception.TikTokException;
import vip.gadfly.tiktok.core.utils.StringUtil;
import vip.gadfly.tiktok.open.cache.IAccessTokenCache;

/**
 * accessToken处理
 *
 * @author OF
 * @date 2017年10月16日
 */
public class AccessTokenConfig {
    private static final Logger log = LoggerFactory.getLogger(AccessTokenConfig.class);

    private static final AccessTokenConfig INSTANCE = new AccessTokenConfig();
    /**
     * 减去秒数
     */
    public static int OutTime = 10;
    private static IAccessTokenCache accessTokenCache;

    public static AccessTokenConfig getInstance() {
        return INSTANCE;
    }

    public IAccessTokenCache getAccessTokenCache() {
        return AccessTokenConfig.accessTokenCache;
    }

    public static void setAccessTokenCache(IAccessTokenCache accessTokenCache) {
        AccessTokenConfig.accessTokenCache = accessTokenCache;
    }

    /**
     * 获取accessToken
     *
     * @return
     */
    public String getAccessToken(String cacheKey, boolean isRefresh) {
        return getAccessTokenResult(cacheKey, isRefresh).getAccessToken();
    }

    public AccessTokenResult setAccessToken(String accessToken) {
        AccessTokenApi tokenApi = new AccessTokenApi();
        AccessTokenResult result = tokenApi.refresh(accessToken);
        return result;

    }

    /**
     * 失败连续三次重复
     */
    public AccessTokenResult refreshAccessToken(String accessToken) {
        AccessTokenResult result = null;
        for (int i = 1; i <= 3; i++) {
            log.debug("获取 access token 第 [" + i + "] 次");
            result = setAccessToken(accessToken);
            if (result != null) {
                return result;
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    /**
     * 缓存中获取 AccessTokenResult
     *
     * @return
     */
    public AccessTokenResult getAccessTokenResult(String cacheKey, boolean isRefresh) {
        String json = accessTokenCache.getStr(cacheKey);
        if (StringUtil.isEmpty(json)) {
            throw new TikTokException(" cache not find cacheKey =" + cacheKey);
        }
        AccessTokenResult result = JSONObject.parseObject(json, AccessTokenResult.class);
        // 判断是否将要过期
        if (!isAvailable(result) && isRefresh) {
            result = refreshAccessToken(result.getAccessToken());
        }
        return result;
    }

    public boolean isAvailable(AccessTokenResult result) {
        Long expiredTime = result.getExpiredTime();
        if (expiredTime == null || expiredTime == 0)
            return false;
        if (result.getErrCode() != 0)
            return false;
        if (expiredTime < System.currentTimeMillis())
            return false;
        return result.getAccessToken() != null;
    }
}
