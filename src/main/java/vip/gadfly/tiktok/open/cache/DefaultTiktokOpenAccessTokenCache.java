package vip.gadfly.tiktok.open.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认本地缓存
 *
 * @author OF
 * @date 2018年7月16日
 */
public class DefaultTiktokOpenAccessTokenCache implements ITiktokOpenAccessTokenCache {
    private static final ConcurrentHashMap<String, String> accessTokenCah = new ConcurrentHashMap<>();

    public Object get(String key) {
        return accessTokenCah.get(key);
    }

    public String getStr(String key) {
        return accessTokenCah.get(key);
    }

    public ITiktokOpenAccessTokenCache set(String key, String value) {
        accessTokenCah.put(key, value);
        return this;
    }

    public ITiktokOpenAccessTokenCache set(String key, String value, Integer time) {
        accessTokenCah.put(key, value);
        return this;
    }

    public ITiktokOpenAccessTokenCache remove(String key) {
        accessTokenCah.remove(key);
        return this;
    }

    public boolean isContainKey(String key) {
        return accessTokenCah.containsKey(key);
    }


}
