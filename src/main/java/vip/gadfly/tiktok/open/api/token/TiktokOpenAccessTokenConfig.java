package vip.gadfly.tiktok.open.api.token;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vip.gadfly.tiktok.core.exception.TikTokException;
import vip.gadfly.tiktok.core.utils.StringUtil;
import vip.gadfly.tiktok.open.cache.ITiktokOpenAccessTokenCache;

/**
 * accessToken处理
 *
 * @author OF
 * @date 2017年10月16日
 */
public class TiktokOpenAccessTokenConfig {
    private static final Logger log = LoggerFactory.getLogger(TiktokOpenAccessTokenConfig.class);

    private static final TiktokOpenAccessTokenConfig INSTANCE = new TiktokOpenAccessTokenConfig();
    /**
     * 减去秒数
     */
    public static int OutTime = 10;
    private ITiktokOpenAccessTokenCache accessTokenCache;

    public static TiktokOpenAccessTokenConfig getInstance() {
        return INSTANCE;
    }

    public static ITiktokOpenAccessTokenCache getAccessTokenCache() {
        return INSTANCE.accessTokenCache;
    }

    public static void setAccessTokenCache(ITiktokOpenAccessTokenCache accessTokenCache) {
        INSTANCE.accessTokenCache = accessTokenCache;
    }

    /**
     * 获取accessToken
     *
     * @return
     */
    public String getAccessToken(String cacheKey, boolean isRefresh) {
        return getAccessTokenResult(cacheKey, isRefresh).getAccessToken();
    }

    public TiktokOpenAccessTokenResult setRefreshToken(String refreshToken) {
        TiktokOpenAccessTokenApi tokenApi = new TiktokOpenAccessTokenApi();
        return tokenApi.refreshAccessToken(refreshToken);

    }

    /**
     * 失败连续三次重复
     */
    public TiktokOpenAccessTokenResult refreshAccessToken(String refreshToken) {
        TiktokOpenAccessTokenResult result = null;
        for (int i = 1; i <= 3; i++) {
            log.debug("获取 access token 第 [" + i + "] 次");
            result = setRefreshToken(refreshToken);
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
    public TiktokOpenAccessTokenResult getAccessTokenResult(String cacheKey, boolean isRefresh) {
        String json = accessTokenCache.getStr(cacheKey);
        if (StringUtil.isEmpty(json)) {
            throw new TikTokException(" cache not find cacheKey =" + cacheKey);
        }
        TiktokOpenAccessTokenResult result = JSONObject.parseObject(json, TiktokOpenAccessTokenResult.class);
        // 判断是否将要过期
        if (!isAvailable(result) || isRefresh) {
            result = refreshAccessToken(result.getRefreshToken());
        }
        return result;
    }

    public boolean isAvailable(TiktokOpenAccessTokenResult result) {
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
