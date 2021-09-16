package vip.gadfly.tiktok.open.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import vip.gadfly.tiktok.core.redis.RedisTemplateTiktokRedisOps;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 默认本地缓存
 *
 * @author OF
 * @date 2018年7月16日
 */
@RequiredArgsConstructor
public class RedisTemplateAccessTokenCache implements IAccessTokenCache {
    private final RedisTemplateTiktokRedisOps redisTemplateTiktokRedisOps;

    public Object get(String key) {
        return redisTemplateTiktokRedisOps.getValue(key);
    }

    public String getStr(String key) {
        return redisTemplateTiktokRedisOps.getValue(key);
    }

    public IAccessTokenCache set(String key, String value) {
        redisTemplateTiktokRedisOps.setValue(key, value, 0 ,null);
        return this;
    }

    public IAccessTokenCache set(String key, String value, Long time) {
        redisTemplateTiktokRedisOps.setValue(key, value, Math.toIntExact(time), TimeUnit.SECONDS);
        return this;
    }

    public IAccessTokenCache remove(String key) {
        redisTemplateTiktokRedisOps.expire(key, 0 , TimeUnit.SECONDS);
        return this;
    }

    public boolean isContainKey(String key) {
        return redisTemplateTiktokRedisOps.getValue(key) != null;
    }

}
