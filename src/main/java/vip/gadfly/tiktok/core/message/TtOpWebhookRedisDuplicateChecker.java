package vip.gadfly.tiktok.core.message;

import vip.gadfly.tiktok.core.redis.BaseTtOpRedisOps;
import vip.gadfly.tiktok.core.util.StringUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author yangyidian
 * @date 2020/07/08
 **/
public class TtOpWebhookRedisDuplicateChecker implements ITtOpWebhookDuplicateChecker {

    private final BaseTtOpRedisOps ttOpRedisOps;

    public TtOpWebhookRedisDuplicateChecker(BaseTtOpRedisOps ttOpRedisOps) {
        this.ttOpRedisOps = ttOpRedisOps;
    }

    @Override
    public boolean isDuplicate(String messageId) {
        String keyId = "tiktok:open:msgid:" + messageId;
        boolean notExist = StringUtil.isEmpty(ttOpRedisOps.getValue(keyId));
        if (notExist) {
            ttOpRedisOps.setValue(keyId, String.valueOf(System.currentTimeMillis()), 6, TimeUnit.MINUTES);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean clearDuplicate(String messageId) {
        String keyId = "tiktok:open:msgid:" + messageId;
        return ttOpRedisOps.expire(keyId, 0, TimeUnit.SECONDS);
    }
}
