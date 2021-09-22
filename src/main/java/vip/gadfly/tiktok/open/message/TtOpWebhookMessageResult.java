package vip.gadfly.tiktok.open.message;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangyidian
 * @date 2020/08/14
 **/
@Data
public class TtOpWebhookMessageResult {

    private Map<String, TtOpWebhookMessageHandleResult> results = new HashMap<>();

    private Map<String, Exception> exceptions = new HashMap<>();

    public void addRouterRuleResult(TtOpWebhookMessageRouterRuleResult routerRuleResult) {
        addResults(routerRuleResult.getResults());
        addExceptions(routerRuleResult.getExceptions());
    }

    /**
     * 如果所有handler都正常处理，也就是exceptions.isEmpty()为true，那么返回success，否则返回failed
     *
     * @return
     */
    public String getDefaultResult() {
        if (exceptions.isEmpty()) {
            return "success";
        } else {
            return "failed";
        }
    }

    private Map<String, TtOpWebhookMessageHandleResult> addResults(Map<String, TtOpWebhookMessageHandleResult> newResults) {
        results.putAll(newResults);
        return results;
    }

    private Map<String, Exception> addExceptions(Map<String, Exception> newExceptions) {
        exceptions.putAll(newExceptions);
        return exceptions;
    }
}
