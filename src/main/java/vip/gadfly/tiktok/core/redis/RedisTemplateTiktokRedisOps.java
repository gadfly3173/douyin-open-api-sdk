package vip.gadfly.tiktok.core.redis;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import vip.gadfly.tiktok.core.utils.lock.RedisTemplateSimpleDistributedLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RequiredArgsConstructor
public class RedisTemplateTiktokRedisOps extends BaseTiktokRedisOps {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setValue(String key, String value, int expire, TimeUnit timeUnit) {
        if (expire <= 0) {
            redisTemplate.opsForValue().set(key, value);
        } else {
            redisTemplate.opsForValue().set(key, value, expire, timeUnit);
        }
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public void expire(String key, int expire, TimeUnit timeUnit) {
        redisTemplate.expire(key, expire, timeUnit);
    }

    @Override
    public Lock getLock(@NonNull String key) {
        return new RedisTemplateSimpleDistributedLock(redisTemplate, key, 60 * 1000);
    }
}
