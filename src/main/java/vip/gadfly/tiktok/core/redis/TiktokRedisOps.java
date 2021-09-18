package vip.gadfly.tiktok.core.redis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 微信Redis相关操作
 * <p>
 * 该接口不承诺稳定, 外部实现请继承{@link BaseTtOpRedisOps}
 *
 * @see BaseTtOpRedisOps 实现需要继承该类
 * @see RedisTemplateTtOpRedisOps redisTemplate实现
 */
public interface TiktokRedisOps {

    String getValue(String key);

    void setValue(String key, String value, int expire, TimeUnit timeUnit);

    Long getExpire(String key);

    void expire(String key, int expire, TimeUnit timeUnit);

    Lock getLock(String key);
}
