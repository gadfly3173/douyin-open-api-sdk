package vip.gadfly.tiktok.open.cache;

import lombok.RequiredArgsConstructor;
import vip.gadfly.tiktok.core.redis.RedisTemplateTtOpRedisOps;

import java.util.concurrent.TimeUnit;

/**
 * 默认本地缓存
 *
 * @author OF
 * @date 2018年7月16日
 */
@RequiredArgsConstructor
public class RedisTemplateTtOpAccessTokenCache implements ITtOpAccessTokenCache {
    private final RedisTemplateTtOpRedisOps redisTemplateTtOpRedisOps;

    public Object get(String key) {
        return redisTemplateTtOpRedisOps.getValue(key);
    }

    public String getStr(String key) {
        return redisTemplateTtOpRedisOps.getValue(key);
    }

    public ITtOpAccessTokenCache set(String key, String value) {
        redisTemplateTtOpRedisOps.setValue(key, value, 0, null);
        return this;
    }

    public ITtOpAccessTokenCache set(String key, String value, Integer time) {
        redisTemplateTtOpRedisOps.setValue(key, value, Math.toIntExact(time), TimeUnit.SECONDS);
        return this;
    }

    public ITtOpAccessTokenCache remove(String key) {
        redisTemplateTtOpRedisOps.expire(key, 0, TimeUnit.SECONDS);
        return this;
    }

    public boolean isContainKey(String key) {
        return redisTemplateTtOpRedisOps.getValue(key) != null;
    }

}
