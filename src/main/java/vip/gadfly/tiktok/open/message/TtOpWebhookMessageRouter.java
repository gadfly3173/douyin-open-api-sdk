package vip.gadfly.tiktok.open.message;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import vip.gadfly.tiktok.core.message.ITtOpWebhookDuplicateChecker;
import vip.gadfly.tiktok.core.redis.BaseTtOpRedisOps;
import vip.gadfly.tiktok.core.util.json.JsonSerializer;
import vip.gadfly.tiktok.core.util.json.TiktokOpenJsonBuilder;
import vip.gadfly.tiktok.open.bean.message.TtOpWebhookMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yangyidian
 * @date 2020/07/08
 **/
@Slf4j
@Data
public class TtOpWebhookMessageRouter {

    private static final int DEFAULT_THREAD_POOL_SIZE = 100;
    private BaseTtOpRedisOps byteDanceRedisOps;
    private List<TtOpWebhookMessageRouterRule> rules = new ArrayList<>();
    private ITtOpWebhookDuplicateChecker messageDuplicateChecker;
    private JsonSerializer jsonSerializer;
    private ExecutorService executorService;

    public TtOpWebhookMessageRouter(ITtOpWebhookDuplicateChecker messageDuplicateChecker) {
        this.messageDuplicateChecker = messageDuplicateChecker;
        this.jsonSerializer = TiktokOpenJsonBuilder.instance();
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("TtOpWebhookMessageRouter-pool-%d").build();
        this.executorService = new ThreadPoolExecutor(DEFAULT_THREAD_POOL_SIZE, DEFAULT_THREAD_POOL_SIZE,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    }

    public TtOpWebhookMessageRouter(ITtOpWebhookDuplicateChecker messageDuplicateChecker, JsonSerializer jsonSerializer) {
        this.messageDuplicateChecker = messageDuplicateChecker;
        this.jsonSerializer = jsonSerializer;
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("TtOpWebhookMessageRouter-pool-%d").build();
        this.executorService = new ThreadPoolExecutor(DEFAULT_THREAD_POOL_SIZE, DEFAULT_THREAD_POOL_SIZE,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    }

    public TtOpWebhookMessageRouter(ITtOpWebhookDuplicateChecker messageDuplicateChecker, JsonSerializer jsonSerializer,
                                    ExecutorService executorService) {
        this.messageDuplicateChecker = messageDuplicateChecker;
        this.jsonSerializer = jsonSerializer;
        this.executorService = executorService;
    }

    /**
     * 如果使用默认的 {@link ExecutorService}，则系统退出前，应该调用该方法.
     */
    public void shutDownExecutorService() {
        this.executorService.shutdown();
    }

    /**
     * 开始一个新的Route规则.
     */
    public TtOpWebhookMessageRouterRule rule() {
        return new TtOpWebhookMessageRouterRule(this);
    }


    public TtOpWebhookMessageResult route(final TtOpWebhookMessage message) {
        return route(message, new HashMap<>(1));
    }

    /**
     * 匹配符合的规则，然后用这些规则中的handlers去处理消息.
     */
    public TtOpWebhookMessageResult route(final TtOpWebhookMessage message, Map<String, Object> context) {
        TtOpWebhookMessageResult messageResult = new TtOpWebhookMessageResult();
        final List<TtOpWebhookMessageRouterRule> matchedRules = new ArrayList<>();
        for (TtOpWebhookMessageRouterRule rule : this.rules) {
            if (rule.test(message)) {
                matchedRules.add((rule));
                if (!rule.isReEnter()) {
                    break;
                }
            }
        }

        if (matchedRules.isEmpty()) {
            return messageResult;
        }

        TtOpWebhookMessageRouterRuleResult ruleResult;
        final List<Future<TtOpWebhookMessageRouterRuleResult>> futures = new ArrayList<>();
        for (final TtOpWebhookMessageRouterRule rule : matchedRules) {
            // 返回最后一个非异步的rule的执行结果
            if (rule.isAsync()) {
                futures.add(this.executorService.submit(() -> rule.handle(message, context)));
                log.debug("futures={}", futures);
            } else {
                ruleResult = rule.handle(message, context);
                messageResult.addRouterRuleResult(ruleResult);
            }
        }

        if (futures.isEmpty()) {
            log.debug("futures is empty={}", futures);
            return messageResult;
        }

        this.executorService.submit(() -> {
            for (Future<TtOpWebhookMessageRouterRuleResult> future : futures) {
                try {
                    messageResult.addRouterRuleResult(future.get());
                    log.debug("future.get={}", future.get());
                } catch (InterruptedException e) {
                    log.error("Error happened when wait task finish", e);
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    log.error("Error happened when wait task finish", e);
                }
            }
        });
        return messageResult;
    }
}
