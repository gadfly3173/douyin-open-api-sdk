package vip.gadfly.tiktok.open.message;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 每个rule里的handlers处理完message后的结果
 *
 * @author yangyidian
 * @date 2020/08/17
 **/
@Data
public class TtOpWebhookMessageRouterRuleResult {
    private Map<ITtOpWebhookMessageHandler, TtOpWebhookMessageHandleResult> results = new HashMap<>();

    private Map<ITtOpWebhookMessageHandler, Exception> exceptions = new HashMap<>();

    public Map<ITtOpWebhookMessageHandler, TtOpWebhookMessageHandleResult> addResult(ITtOpWebhookMessageHandler handler, TtOpWebhookMessageHandleResult result) {
        results.put(handler, result);
        return results;
    }

    public Map<ITtOpWebhookMessageHandler, Exception> addException(ITtOpWebhookMessageHandler handler, Exception e) {
        exceptions.put(handler, e);
        return exceptions;
    }
}
