package vip.gadfly.tiktok.open.message;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import vip.gadfly.tiktok.open.bean.message.TtOpWebhookMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 事件由哪些handler进行处理
 *
 * @author yangyidian
 * @date 2020/07/08
 **/
@Slf4j
@Data
public class TtOpWebhookMessageRouterRule {

    private final TtOpWebhookMessageRouter router;
    private boolean async = true;
    private String event;
    private String msgType;
    private List<ITtOpWebhookMessageHandler> handlers = new ArrayList<>();

    public TtOpWebhookMessageRouterRule(TtOpWebhookMessageRouter router) {
        this.router = router;
    }

    public TtOpWebhookMessageRouterRule event(String event) {
        this.event = event;
        return this;
    }

    public TtOpWebhookMessageRouterRule msgType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    /**
     * 设置是否异步执行，默认是true
     */
    public TtOpWebhookMessageRouterRule async(boolean async) {
        this.async = async;
        return this;
    }

    /**
     * 设置消息处理器
     */
    public TtOpWebhookMessageRouterRule addHandler(ITtOpWebhookMessageHandler handler) {
        this.handlers.add(handler);
        return this;
    }


    public TtOpWebhookMessageRouter end() {
        this.router.getRules().add(this);
        return this.router;
    }

    /**
     * 使用该规则对应的所有handlers处理消息
     *
     * @return
     */
    public TtOpWebhookMessageRouterRuleResult handle(TtOpWebhookMessage message, Map<String, Object> context) {
        TtOpWebhookMessageRouterRuleResult ruleResult = new TtOpWebhookMessageRouterRuleResult();
        TtOpWebhookMessageHandleResult handlerResult;
        for (ITtOpWebhookMessageHandler handler : this.handlers) {
            String messageId = message.getMsgId();
            if (isMessageHandledByHandler(handler, message)) {
                log.info("重复消息不做处理");
            } else {
                try {
                    handlerResult = handler.handle(message, context);
                    ruleResult.addResult(handler, handlerResult);
                } catch (Exception e) {
                    log.error("handler[{}]处理消息[{}]失败", handler.getHandlerName(), messageId);
                    // 如果这条消息处理报错，那么清除这条消息的重复状态，这样字节服务再次推送这条消息的时候，可以再次处理
                    log.error("消息处理失败，清除消息重复状态===>{}", router.getJsonSerializer().toJson(message));
                    log.error(e.getMessage(), e);
                    ruleResult.addException(handler, e);
                    router.getMessageDuplicateChecker().clearDuplicate(messageId);
                }
            }
        }
        return ruleResult;
    }

    /**
     * 测试事件与规则是否匹配
     */
    protected boolean test(TtOpWebhookMessage message) {
        return isEventMatch(message) && isMsgTypeMatch(message);
    }

    private boolean isEventMatch(TtOpWebhookMessage message) {
        return event == null || event.equalsIgnoreCase(message.getEvent());
    }

    private boolean isMsgTypeMatch(TtOpWebhookMessage message) {
        try {
            return msgType == null || msgType.equalsIgnoreCase(message.getContent().getMessageType());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAsync() {
        return this.async;
    }

    /**
     * message是否已经被handler处理过了
     *
     * @param handler
     * @param message
     * @return
     */
    private boolean isMessageHandledByHandler(ITtOpWebhookMessageHandler handler, TtOpWebhookMessage message) {
        if (handler.repeatable()) {
            return false;
        }
        String messageId = message.getMsgId();
        if (router.getMessageDuplicateChecker() == null) {
            log.warn("没有配置消息重复检查器，不进行消息重复性检查");
            return false;
        }
        log.info("进行消息重复性检查: {}", messageId);
        return router.getMessageDuplicateChecker().isDuplicate(messageId);
    }

}
