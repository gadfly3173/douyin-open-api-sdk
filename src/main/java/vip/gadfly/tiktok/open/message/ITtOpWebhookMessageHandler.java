package vip.gadfly.tiktok.open.message;

import vip.gadfly.tiktok.open.bean.message.TtOpWebhookMessage;

import java.util.Map;

/**
 * @author yangyidian
 * @date 2020/07/08
 **/
public interface ITtOpWebhookMessageHandler {

    /**
     * 处理消息
     *
     * @param message
     * @param context 可以加一些handler处理message时需要的参数
     * @return
     */
    TtOpWebhookMessageHandleResult handle(TtOpWebhookMessage message, Map<String, Object> context);

    /**
     * 获取handler名字
     *
     * @return
     */
    default String getHandlerName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 同一事件重复推送时, 是否可以被该handler重复处理
     *
     * @return
     */
    default boolean repeatable() {
        return false;
    }
}
