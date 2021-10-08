package vip.gadfly.tiktok.open.common;

import com.google.common.collect.Multimap;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.open.api.TtOpOAuth2Service;
import vip.gadfly.tiktok.open.api.TtOpUserInfoService;
import vip.gadfly.tiktok.open.api.TtOpVideoService;
import vip.gadfly.tiktok.open.bean.oauth2.TtOpJsapiSignature;

import java.util.Map;

/**
 * @author yangyidian
 * @date 2020/09/18
 **/
public interface ITtOpBaseService {

    String get(String url);

    <T> T get(String url, Class<T> t);

    <T> T post(String url, Object request, Class<T> t);

    <T> T postWithHeaders(String url, Multimap<String, String> headers, Object request, Class<T> t);

    /**
     * 获取当前的应用配置
     *
     * @return 应用配置
     */
    TtOpConfigStorage getTtOpConfigStorage();

    /**
     * 设置 {@link TtOpConfigStorage} 的实现
     *
     * @param tiktokConfigProvider .
     */
    void setTiktokOpenConfigStorage(TtOpConfigStorage tiktokConfigProvider);

    /**
     * 注入多个 {@link TtOpConfigStorage} 的实现. 并为每个 {@link TtOpConfigStorage} 赋予不同的 {@link String clientKey} 值
     * 随机采用一个{@link String clientKey}进行Http初始化操作
     *
     * @param configStorages TtOpConfigStorage map
     */
    void setMultiConfigStorages(Map<String, TtOpConfigStorage> configStorages);

    /**
     * 注入多个 {@link TtOpConfigStorage} 的实现. 并为每个 {@link TtOpConfigStorage} 赋予不同的 {@link String label} 值
     *
     * @param configStorages TtOpConfigStorage map
     * @param defaultClientKey   设置一个{@link TtOpConfigStorage} 所对应的{@link String clientKey}进行Http初始化
     */
    void setMultiConfigStorages(Map<String, TtOpConfigStorage> configStorages, String defaultClientKey);

    /**
     * Map里 加入新的 {@link TtOpConfigStorage}，适用于动态添加新的抖音开发者应用配置.
     *
     * @param clientKey          抖音开发者应用id
     * @param configStorages 新的微信配置
     */
    void addConfigStorage(String clientKey, TtOpConfigStorage configStorages);

    /**
     * 从 Map中 移除 {@link String clientKey} 所对应的 {@link TtOpConfigStorage}，适用于动态移除抖音开发者应用配置.
     *
     * @param clientKey 对应抖音开发者应用的标识
     */
    void removeConfigStorage(String clientKey);

    /**
     * 进行相应的抖音开发者应用切换.
     *
     * @param clientKey 抖音开发者应用标识
     * @return 切换是否成功 boolean
     */
    boolean switchover(String clientKey);

    /**
     * 设置重试间隔毫秒
     *
     * @param retrySleepMillis 重试间隔毫秒
     */
    void setRetrySleepMillis(int retrySleepMillis);

    /**
     * 设置最大重试次数
     *
     * @param maxRetryTimes 最大重试次数
     */
    void setMaxRetryTimes(int maxRetryTimes);

    /**
     * 获取当前使用的用户open id
     *
     * @return openid
     */
    String getClientKey();

    /**
     * 获取当前用户的access token
     *
     * @return access token
     */
    String getAccessToken(String openid);

    /**
     * 获取当前用户的access token
     *
     * @param forceRefresh 是否强制刷新
     * @return access token
     */
    String getAccessToken(String openid, boolean forceRefresh);

    /**
     * 获取当前app的指定ticket
     *
     * @param type ticket类型
     * @return ticket
     */
    String getTicket(TtOpTicketType type);

    /**
     * 获取当前app的指定ticket
     *
     * @param type         ticket类型
     * @param forceRefresh 是否强制刷新
     * @return ticket
     */
    String getTicket(TtOpTicketType type, boolean forceRefresh);

    /**
     * 获取base 抖音开放服务 http url
     *
     * @return url
     */
    String getHttpUrl();

    /**
     * 生成抖音开放服务的jsapi签名参数
     *
     * @param url 页面url
     * @return 签名信息
     */
    TtOpJsapiSignature createJsapiSignature(String url);

    /**
     * 检查webhook签名是否正确
     *
     * @param body 消息内容
     * @return 是否正确
     */
    boolean checkWebhookSignature(String xSignature, String body);

    TtOpOAuth2Service getTtOpOAuth2Service();

    void setTtOpOAuth2Service(TtOpOAuth2Service ttOpOAuth2Service);

    TtOpUserInfoService getTtOpUserInfoService();

    void setTtOpUserInfoService(TtOpUserInfoService ttOpUserInfoService);

    TtOpVideoService getTtOpVideoService();

    void setTtOpVideoService(TtOpVideoService ttOpVideoService);
}
