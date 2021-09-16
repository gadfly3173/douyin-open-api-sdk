package vip.gadfly.tiktok.open.base;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import vip.gadfly.tiktok.config.TiktokOpenConfigStorage;
import vip.gadfly.tiktok.core.OkHttp4;
import vip.gadfly.tiktok.core.exception.TikTokException;
import vip.gadfly.tiktok.core.utils.StringUtil;
import vip.gadfly.tiktok.core.utils.TiktokOpenConfigStorageHolder;
import vip.gadfly.tiktok.open.api.token.TiktokOpenAccessTokenConfig;

import java.io.File;
import java.text.MessageFormat;
import java.util.Map;

/**
 * 接口基类
 *
 * <pre>
 * 处理请求与accessToken 处理
 * </pre>
 *
 * @author OF
 * @date 2018年7月13日
 */
@Slf4j
public abstract class AbstractTiktokOpenApiBase implements ITiktokOpenBaseService, IRetryableExecutor {

    private static final String ACCESS_TOKEN_PREFIX = "{0}:{1}:access_token";
    private static int retrySleepMillis = 1000;
    private static int maxRetryTimes = 5;
    private String openId;
    private String accessToken;

    private Map<String, TiktokOpenConfigStorage> configStorageMap;

    public AbstractTiktokOpenApiBase(String openId) {
        this.openId = openId;
    }

    public AbstractTiktokOpenApiBase() {
    }

    /**
     * POST 请求数据
     *
     * @param request json 内容
     * @return 返回 TiktokOpenApiResponse 内容
     */
    public static TiktokOpenApiResponse sendPost(TiktokOpenApiRequest request) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPostJson(request.getUrl(),
                        request.toJson());
                return new TiktokOpenApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public static TiktokOpenApiResponse sendPost(String url, TiktokOpenApiRequest request) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPostJson(url, request.toJson());
                return new TiktokOpenApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public static TiktokOpenApiResponse sendPost(String url, File file, String mediaType) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPost(url, file, mediaType);
                return new TiktokOpenApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    /**
     * POST 请求数据
     *
     * @param url json 内容
     * @return 返回 json 内容
     */
    public static TiktokOpenApiResponse sendPost(String url, String json) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPostJson(url, json);
                return new TiktokOpenApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public static TiktokOpenApiResponse sendGet(String url) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpGet(url, null);
                return new TiktokOpenApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    private static void responseExceptionHandler(int retryTimes, TikTokException e) {
        if (retryTimes + 1 > maxRetryTimes) {
            log.warn("重试达到最大次数【{}】", maxRetryTimes);
            // 最后一次重试失败后，直接抛出异常，不再等待
            throw new TikTokException("抖音服务端异常，超出重试次数");
        }
        // 2100004 系统繁忙, 1000ms后重试
        if (e.getErrorCode() != TiktokOpenApiResponse.TIKTOK_OPEN_BUSY_CODE) {
            throw e;
        }
        int sleepMillis = retrySleepMillis * (1 << retryTimes);
        try {
            log.debug("抖音系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
        }
    }

    public TiktokOpenConfigStorage getTiktokOpenConfigStorage() {
        if (this.configStorageMap.size() == 1) {
            // 只有一个公众号，直接返回其配置即可
            return this.configStorageMap.values().iterator().next();
        }

        return this.configStorageMap.get(TiktokOpenConfigStorageHolder.get());
    }

    /**
     * 设置 {@link TiktokOpenConfigStorage} 的实现. 兼容老版本
     *
     * @param tiktokConfigProvider .
     */
    public void setTiktokOpenConfigStorage(TiktokOpenConfigStorage tiktokConfigProvider) {
        final String defaultMpId = tiktokConfigProvider.getAppId();
        this.setMultiConfigStorages(ImmutableMap.of(defaultMpId, tiktokConfigProvider), defaultMpId);
    }

    /**
     * 注入多个 {@link TiktokOpenConfigStorage} 的实现. 并为每个 {@link TiktokOpenConfigStorage} 赋予不同的 {@link String mpId} 值
     * 随机采用一个{@link String mpId}进行Http初始化操作
     *
     * @param configStorages TiktokOpenConfigStorage map
     */
    public void setMultiConfigStorages(Map<String, TiktokOpenConfigStorage> configStorages) {
        this.setMultiConfigStorages(configStorages, configStorages.keySet().iterator().next());
    }

    /**
     * 注入多个 {@link TiktokOpenConfigStorage} 的实现. 并为每个 {@link TiktokOpenConfigStorage} 赋予不同的 {@link String label} 值
     *
     * @param configStorages TiktokOpenConfigStorage map
     * @param defaultMpId    设置一个{@link TiktokOpenConfigStorage} 所对应的{@link String mpId}进行Http初始化
     */
    public void setMultiConfigStorages(Map<String, TiktokOpenConfigStorage> configStorages, String defaultMpId) {
        this.configStorageMap = Maps.newHashMap(configStorages);
        TiktokOpenConfigStorageHolder.set(defaultMpId);
    }

    /**
     * Map里 加入新的 {@link TiktokOpenConfigStorage}，适用于动态添加新的微信公众号配置.
     *
     * @param mpId           公众号id
     * @param configStorages 新的微信配置
     */
    public void addConfigStorage(String mpId, TiktokOpenConfigStorage configStorages) {
        synchronized (this) {
            if (this.configStorageMap == null) {
                this.setTiktokOpenConfigStorage(configStorages);
            } else {
                TiktokOpenConfigStorageHolder.set(mpId);
                this.configStorageMap.put(mpId, configStorages);
            }
        }
    }

    /**
     * 从 Map中 移除 {@link String mpId} 所对应的 {@link TiktokOpenConfigStorage}，适用于动态移除微信公众号配置.
     *
     * @param mpId 对应公众号的标识
     */
    public void removeConfigStorage(String mpId) {
        synchronized (this) {
            if (this.configStorageMap.size() == 1) {
                this.configStorageMap.remove(mpId);
                log.warn("已删除最后一个公众号配置：{}，须立即使用setTiktokOpenConfigStorage或setMultiConfigStorages添加配置", mpId);
                return;
            }
            if (TiktokOpenConfigStorageHolder.get().equals(mpId)) {
                this.configStorageMap.remove(mpId);
                final String defaultMpId = this.configStorageMap.keySet().iterator().next();
                TiktokOpenConfigStorageHolder.set(defaultMpId);
                log.warn("已删除默认公众号配置，公众号【{}】被设为默认配置", defaultMpId);
                return;
            }
            this.configStorageMap.remove(mpId);
        }
    }

    /**
     * 进行相应的公众号切换.
     *
     * @param mpId 公众号标识
     * @return 切换是否成功 boolean
     */
    public boolean switchover(String mpId) {
        if (this.configStorageMap.containsKey(mpId)) {
            TiktokOpenConfigStorageHolder.set(mpId);
            return true;
        }

        log.error("无法找到对应【{}】的公众号配置信息，请核实！", mpId);
        return false;
    }

    public void setRetrySleepMillis(int retrySleepMillis) {
        AbstractTiktokOpenApiBase.retrySleepMillis = retrySleepMillis;
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        AbstractTiktokOpenApiBase.maxRetryTimes = maxRetryTimes;
    }

    public String getOpenId() {
        return openId;
    }

    public AbstractTiktokOpenApiBase setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getAppId() {
        TiktokOpenConfigStorage configStorage = getTiktokOpenConfigStorage();
        return configStorage.getAppId();
    }

    public String getHttpUrl() {
        TiktokOpenConfigStorage configStorage = getTiktokOpenConfigStorage();
        return configStorage.getHostConfig().getTiktokOpenHost();
    }

    /**
     * 缓存本地的 key (openId / appId)
     *
     * @return
     */
    public String getCacheKey() {
        if (!StringUtil.isEmpty(getOpenId())) {
            return MessageFormat.format(ACCESS_TOKEN_PREFIX, getAppId(), getOpenId());
        } else {
            return MessageFormat.format(ACCESS_TOKEN_PREFIX, getAppId(), "");
        }
    }

    /**
     * 当缓存中获取 token
     *
     * @return
     */
    public String getAccessToken() {
        //如果有指定 tokenCode
        if (!StringUtil.isEmpty(this.accessToken)) {
            return accessToken;
        }
        //从缓存里获取
        TiktokOpenAccessTokenConfig config = TiktokOpenAccessTokenConfig.getInstance();
        return config.getAccessToken(getCacheKey(), true);
    }

    public AbstractTiktokOpenApiBase setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public AbstractTiktokOpenApiBase withAccessToken(String accessToken) {
        return this.setAccessToken(accessToken);
    }

    public AbstractTiktokOpenApiBase withOpenId(String openId) {
        return this.setOpenId(openId);
    }

    public abstract String scope();
}
