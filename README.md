# wptai-douyin-api 快速接入 几行代码实现抖音接入

开发中，暂不可直接使用

参考项目：
- [https://gitee.com/hudan870614/wptai-douyin-api](https://gitee.com/hudan870614/wptai-douyin-api)
- [https://github.com/yydzxz/ByteDanceOpen](https://github.com/yydzxz/ByteDanceOpen)
- [https://github.com/Wechat-Group/WxJava](https://github.com/Wechat-Group/WxJava)

## 架构
本SDK应当以**单例**的形式被调用。

整体架构与WxJava项目接近，提供了一个默认实现`DefaultTtOpServiceImpl.java`。其中的各api接口实现在各个子service中。

使用时需要修改子service的实现的话就去实现其对应的interface，通过`ITtOpBaseService.setXXXService(yourSubService)`来实现。
而如果需要修改`DefaultTtOpServiceImpl.java`中的实现，也就是覆盖`AbstractTtOpApiBase.java`的实现时，
请不要直接实现（implements） `ITtOpBaseService`，而是继承（extends） `AbstractTtOpApiBase`。

* 自动更新用户的 access/refresh token 功能未实现。

## 项目介绍
   为抖音开发者提供快速接入方案、未依赖任何第三方mvc框架，支持各类 java web 框架接入 

## 安装教程
### Maven引用
```xml
	<dependency>
		<groupId>vip.gadfly</groupId>
		<artifactId>douyin-open-api-sdk</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>	
```
### 自行打包
参阅：[编译](#编译)

## 规范说明
- 类 相关：
  * Service 类: 接口调用及处理类
  * Request 类: 接口入参实实体类
  * Result 类：接口出参实体类

## 使用说明

- 默认的json序列化/反序列化包：`gson`
- 默认的http client：`okhttp 4`
- 项目给出了RedisTemplate的实现，但是`spring-data-redis`是provided scope的

### 加载配置文件及指定配置参数（Spring Boot 2.x）

1.在 `application.yml` 中添加如下配置 *以下配置仅供参考*
```yaml
tt:
  op:
    useRedis: true
    configs:
      - clientKey: awxxxxxxxxx
        clientSecret: 87111aaaaaaa1111xxxx11
```

2. 加载及初始化
```java
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(TtOpConfiguration.TtOpProperties.class)
public class TtOpConfiguration {
    private final TtOpProperties properties;
    private final RedisTemplate<String, String> redisTemplate;

    @Bean
    public ITtOpBaseService ttBaseService() {
        final List<TtOpProperties.TtOpConfig> configs = this.properties.getConfigs();
        if (CollectionUtils.isEmpty(configs)) {
            throw new RuntimeException("抖音配置无效，请检查！");
        }

        RedisTemplateTtOpRedisOps redisOps = new RedisTemplateTtOpRedisOps(redisTemplate);
        DefaultTtOpServiceImpl service = new DefaultTtOpServiceImpl();
        service.setMultiConfigStorages(configs.stream().map(a -> {
            TtOpDefaultConfigImpl configStorage;
            if (this.properties.isUseRedis()) {
                configStorage = new TtOpRedisConfigImpl(redisOps, "tiktok_open");
            } else {
                configStorage = new TtOpDefaultConfigImpl();
            }
            configStorage.setAppId(a.getClientKey());
            configStorage.setAppSecret(a.getClientSecret());
            return configStorage;
        }).collect(Collectors.toMap(TtOpDefaultConfigImpl::getAppId, a -> a, (o, n) -> o)));

        // 设置http client，okhttp是默认值，可以不设置
        service.setTiktokOpenHttpClient(new OkHttpTtOpHttpClient());
        return service;
    }

    @Data
    @ConfigurationProperties(prefix = "tt.op")
    public static class TtOpProperties {
        /**
         * 是否使用redis存储access token
         */
        private boolean useRedis;

        /**
         * 多个抖音开放应用配置信息
         */
        private List<TtOpConfig> configs;

        @Data
        public static class TtOpConfig {
            /**
             * 设置抖音开放应用的appId
             */
            private String clientKey;

            /**
             * 设置抖音开放应用的app secret
             */
            private String clientSecret;
        }
    }
}
```
### Webhook消息路由
项目提供了 Webhook 消息的路由器，可以针对不同的消息/事件类型配置不同的处理。

配置文件
```java
@Configuration
@RequiredArgsConstructor
public class TtOpConfiguration {
    // 这些Handler都需要自己编写，自行针对不同的场景进行配置即可。
    private final LogHandler logHandler;
    private final NullHandler nullHandler;
    private final VerifyWebhookHandler verifyWebhookHandler;
    
    @Bean
    public TtOpWebhookMessageRouter messageRouter() {
        RedisTemplateTtOpRedisOps redisOps = new RedisTemplateTtOpRedisOps(redisTemplate);
        final TtOpWebhookMessageRouter messageRouter =
                new TtOpWebhookMessageRouter(new TtOpWebhookRedisDuplicateChecker(redisOps));

        // 默认async是true，也就是异步执行，可以在这些异步处理里加上专用的日志记录等
        messageRouter.rule().addHandler(this.logHandler).next();
        // 可以指定event来让这个事件的情况都进入某个handler。对于私信类事件还可以指定msgType
        // msgType根据收到消息中的content.messageType字段来区分。event 和 msgType 的判断都忽略大小写。
        // 如果给非私信事件配置msgType，会因为取不到消息中的content.messageType而认为不符合路由
        // 因此对于非私信事件，必须将msgType置为null或不设置
        //
        // 这里使用verify webhook仅为示例，实际开发中这个事件直接在controller里处理更简单
        messageRouter.rule().async(false).event(WebhookEventType.VERIFY_WEBHOOK).addHandler(this.verifyWebhookHandler).end();
        // 不指定event则这个handler处理所有的事件。路由规则的设置依赖ArrayList，因此需要注意顺序，这个兜底的路由需要放在最后。
        messageRouter.rule().async(false).addHandler(this.nullHandler).end();
        return messageRouter;
    }
}
```
Handler
```java
public abstract class AbstractHandler implements ITtOpWebhookMessageHandler {
    protected static final Logger log = org.slf4j.LoggerFactory.getLogger(AbstractHandler.class);
}

@Service
public class LogHandler extends AbstractHandler {
    @Override
    public TtOpWebhookMessageHandleResult handle(TtOpWebhookMessage ttOpWebhookMessage, Map<String, Object> map) {
        log.info("接收到抖音webhook请求消息，内容：{}", JSONObject.toJSONString(ttOpWebhookMessage));
        return null;
    }
}

@Service
public class VerifyWebhookHandler extends AbstractHandler {
    @Override
    public TtOpWebhookMessageHandleResult handle(TtOpWebhookMessage message, Map<String, Object> map) {
        log.info("VerifyWebhookChallenge为：{}", message.getContent().getChallenge());
        TtOpWebhookMessageHandleResult result = new TtOpWebhookMessageHandleResult();
        result.setHandleResult(message.getContent());
        return result;
    }
}

@Service
public class NullHandler extends AbstractHandler {
    @Override
    public TtOpWebhookMessageHandleResult handle(TtOpWebhookMessage ttOpWebhookMessage, Map<String, Object> map) {
        log.info("进入了默认处理");
        return null;
    }
}
```
在controller中使用
```java
@RestController
@Slf4j
public class CallbackController {
    @Autowired
    private ITtOpBaseService ttOpBaseService;
    @Autowired
    private TtOpWebhookMessageRouter messageRouter;

    @PostMapping("/tiktok_open_webhook/{clientKey}")
    public Object handlePostTtOpWebhook(@RequestBody String body, @RequestHeader HttpHeaders headers,
                                        @PathVariable String clientKey) {
        if (!ttOpBaseService.switchover(clientKey)) {
            throw new IllegalArgumentException(String.format("未找到对应clientKey=[%s]的配置，请核实！", clientKey));
        }
        if (!ttOpBaseService.checkWebhookSignature(headers.getFirst("X-Douyin-Signature"), body)) {
            throw new FailedException("非法请求，可能属于伪造的请求！");
        }
        TtOpWebhookMessage message = TtOpWebhookMessage.fromJson(body);
        // 抖音的webhook验证要求返回的内容是一个包含challenge的json，相比于走路由器，直接处理消息后做个if直接return更简单
        if (message.getEvent().equalsIgnoreCase(TtOpConst.WebhookEventType.VERIFY_WEBHOOK)) {
            return message.getContent();
        }
        TtOpWebhookMessageResult result = messageRouter.route(message);
        log.info("result:{}", result);
        return result.getDefaultResult();
    }
}
```

## 编译
项目使用了lombok来简化代码，请在你的IDE中安装对应的插件。

由于lombok是在编译过程中增加字节码的形式来增加语句，因此不做配置时使用 `maven-source-plugin`
打包出的源码包会在IDE中提示字节码与源码不同。为了解决这个问题，项目中引入了 `lombok-maven-plugin`
来进行 delombok。打包时需要指定一下profile id。例如：
```bash
mvn clean install -Pdelombok -f pom.xml clean
```
 
 