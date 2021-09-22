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
    private Map<String, TtOpWebhookMessageHandleResult> results = new HashMap<>();

    private Map<String, Exception> exceptions = new HashMap<>();

    public Map<String, TtOpWebhookMessageHandleResult> addResult(String handlerName, TtOpWebhookMessageHandleResult result) {
        results.put(handlerName, result);
        return results;
    }

    public Map<String, Exception> addException(String handlerName, Exception e) {
        exceptions.put(handlerName, e);
        return exceptions;
    }
}
