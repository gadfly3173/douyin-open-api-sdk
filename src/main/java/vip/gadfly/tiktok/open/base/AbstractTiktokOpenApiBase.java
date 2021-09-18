package vip.gadfly.tiktok.open.base;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import vip.gadfly.tiktok.config.TiktokOpenConfigStorage;
import vip.gadfly.tiktok.core.enums.TiktokOpenTicketType;
import vip.gadfly.tiktok.core.exception.ITiktokOpenError;
import vip.gadfly.tiktok.core.exception.TikTokException;
import vip.gadfly.tiktok.core.exception.TiktokOpenErrorException;
import vip.gadfly.tiktok.core.exception.TiktokOpenErrorMsgEnum;
import vip.gadfly.tiktok.core.http.ITiktokOpenHttpClient;
import vip.gadfly.tiktok.core.http.OkHttp4;
import vip.gadfly.tiktok.core.utils.StringUtil;
import vip.gadfly.tiktok.core.utils.TiktokOpenConfigStorageHolder;
import vip.gadfly.tiktok.core.utils.crypto.SHA1;
import vip.gadfly.tiktok.core.utils.crypto.SignUtil;
import vip.gadfly.tiktok.open.api.token.TiktokOpenAccessTokenApi;
import vip.gadfly.tiktok.open.api.token.TiktokOpenAccessTokenConfig;
import vip.gadfly.tiktok.open.api.token.TiktokOpenAccessTokenResult;
import vip.gadfly.tiktok.open.base.entity.TiktokOpenJsapiSignature;

import java.io.File;
import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.locks.Lock;

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
    private int retrySleepMillis = 1000;
    private int maxRetryTimes = 5;
    private String openId;
    private String accessToken;
    @Getter
    @Setter
    private ITiktokOpenHttpClient tiktokOpenHttpClient;

    private Map<String, TiktokOpenConfigStorage> configStorageMap;

    public AbstractTiktokOpenApiBase(String openId, ITiktokOpenHttpClient tiktokOpenHttpClient) {
        this.openId = openId;
        this.tiktokOpenHttpClient = tiktokOpenHttpClient;
    }

    public AbstractTiktokOpenApiBase() {
    }

    /**
     * POST 请求数据
     *
     * @param request json 内容
     * @return 返回 TiktokOpenApiResponse 内容
     */
    public TiktokOpenApiResponse sendPost(TiktokOpenApiRequest request) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPostJson(request.getUrl(),
                        request.toJson());
                return new TiktokOpenApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < this.maxRetryTimes);
        log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public TiktokOpenApiResponse sendPost(String url, TiktokOpenApiRequest request) {
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

    public TiktokOpenApiResponse sendPost(String url, File file, String mediaType) {
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
    public TiktokOpenApiResponse sendPost(String url, String json) {
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

    public TiktokOpenApiResponse sendGet(String url) {
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

    private void responseExceptionHandler(int retryTimes, TikTokException e) {
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

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public String get(String url) {
        return get(url, String.class);
    }

    @Override
    public <T> T get(String url, Class<T> t) {
        return retryableExecuteRequest(
                (url2, headers, request2, t2) -> getInternal(url2, t2), url, null, null, t);
    }

    private <T> T getInternal(String url, Class<T> t) {
        return executeRequest((uriWithCommonParam, headers, request, t2) ->
                                      getTiktokOpenHttpClient().get(uriWithCommonParam, t2), url, null, null, t);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> t) {
        return retryableExecuteRequest(
                (url2, headers, request2, t2) -> postInternal(url2, request2, t2),
                url, null, request, t);
    }

    private <T> T postInternal(String url, Object request, Class<T> t) {
        return executeRequest((uriWithCommonParam, headers2, request2, t2) ->
                                      getTiktokOpenHttpClient().post(uriWithCommonParam, request2, t2), url, null, request, t);
    }

    @Override
    public <T> T postWithHeaders(String url, Multimap<String, String> headers, Object request, Class<T> t) {
        return retryableExecuteRequest(this::postWithHeadersInternal, url, headers, request, t);
    }

    private <T> T postWithHeadersInternal(String url, Multimap<String, String> headers, Object request, Class<T> t) {
        return executeRequest((uriWithCommonParam, headers2, request2, t2) ->
                                      getTiktokOpenHttpClient().postWithHeaders(uriWithCommonParam, headers2, request2, t2), url, headers, request, t);
    }

    @Override
    public boolean shouldRetry(ITiktokOpenError error) {
        return TiktokOpenErrorMsgEnum.CODE_2100004.getCode() == error.errorCode();
    }

    /**
     * 调用字节跳动服务器接口
     * 统一在url上添加component_access_token 和 component_appid参数
     *
     * @param executable
     * @param url
     * @param request
     * @param t
     * @param <T>
     * @return
     */
    private <T> T executeRequest(IExecutable<T> executable, String url, Multimap<String, String> headers, Object request, Class<T> t) {
        try {
            return executable.execute(url, headers, request, t);
        } catch (TiktokOpenErrorException e) {
            ITiktokOpenError error = e.getError();
            if (!error.checkSuccess()) {
                log.error("\n【请求地址】: {}\n【错误信息】: {}", url, e.getError());
                throw new TiktokOpenErrorException(error, e);
            }
            return null;
        }
    }

    public TiktokOpenConfigStorage getTiktokOpenConfigStorage() {
        if (this.configStorageMap.size() == 1) {
            // 只有一个公众号，直接返回其配置即可
            return this.configStorageMap.values().iterator().next();
        }

        return this.configStorageMap.get(TiktokOpenConfigStorageHolder.get());
    }

    public void setTiktokOpenConfigStorage(TiktokOpenConfigStorage tiktokConfigProvider) {
        final String defaultMpId = tiktokConfigProvider.getAppId();
        this.setMultiConfigStorages(ImmutableMap.of(defaultMpId, tiktokConfigProvider), defaultMpId);
    }

    public void setMultiConfigStorages(Map<String, TiktokOpenConfigStorage> configStorages) {
        this.setMultiConfigStorages(configStorages, configStorages.keySet().iterator().next());
    }

    public void setMultiConfigStorages(Map<String, TiktokOpenConfigStorage> configStorages, String defaultAppId) {
        this.configStorageMap = Maps.newHashMap(configStorages);
        TiktokOpenConfigStorageHolder.set(defaultAppId);
    }

    public void addConfigStorage(String appId, TiktokOpenConfigStorage configStorages) {
        synchronized (this) {
            if (this.configStorageMap == null) {
                this.setTiktokOpenConfigStorage(configStorages);
            } else {
                TiktokOpenConfigStorageHolder.set(appId);
                this.configStorageMap.put(appId, configStorages);
            }
        }
    }

    public void removeConfigStorage(String appId) {
        synchronized (this) {
            if (this.configStorageMap.size() == 1) {
                this.configStorageMap.remove(appId);
                log.warn("已删除最后一个公众号配置：{}，须立即使用setTiktokOpenConfigStorage或setMultiConfigStorages添加配置", appId);
                return;
            }
            if (TiktokOpenConfigStorageHolder.get().equals(appId)) {
                this.configStorageMap.remove(appId);
                final String defaultMpId = this.configStorageMap.keySet().iterator().next();
                TiktokOpenConfigStorageHolder.set(defaultMpId);
                log.warn("已删除默认公众号配置，公众号【{}】被设为默认配置", defaultMpId);
                return;
            }
            this.configStorageMap.remove(appId);
        }
    }

    @Override
    public boolean switchover(String appId) {
        if (this.configStorageMap.containsKey(appId)) {
            TiktokOpenConfigStorageHolder.set(appId);
            return true;
        }

        log.error("无法找到对应【{}】的公众号配置信息，请核实！", appId);
        return false;
    }

    @Override
    public void setRetrySleepMillis(int retrySleepMillis) {
        this.retrySleepMillis = retrySleepMillis;
    }

    @Override
    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

    @Override
    public String getOpenId() {
        return openId;
    }

    @Override
    public ITiktokOpenBaseService setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    @Override
    public String getAppId() {
        TiktokOpenConfigStorage configStorage = getTiktokOpenConfigStorage();
        return configStorage.getAppId();
    }

    @Override
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
    @Override
    public String getAccessToken() {
        return getAccessToken(false);
    }

    @Override
    public ITiktokOpenBaseService setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    @Override
    public String getAccessToken(boolean isRefresh) {
        //如果有指定 tokenCode
        if (!StringUtil.isEmpty(this.accessToken)) {
            return accessToken;
        }
        if (StringUtil.isEmpty(getOpenId())) {
            return getTicket(TiktokOpenTicketType.CLIENT);
        }
        //从缓存里获取
        TiktokOpenAccessTokenConfig config = TiktokOpenAccessTokenConfig.getInstance();
        return config.getAccessToken(getCacheKey(), isRefresh);
    }

    @Override
    public String getTicket(TiktokOpenTicketType type) {
        return this.getTicket(type, false);
    }

    @Override
    public String getTicket(TiktokOpenTicketType type, boolean forceRefresh) {
        if (forceRefresh) {
            this.getTiktokOpenConfigStorage().expireTicket(type);
        }

        if (this.getTiktokOpenConfigStorage().isTicketExpired(type)) {
            Lock lock = this.getTiktokOpenConfigStorage().getTicketLock(type);
            lock.lock();
            try {
                if (this.getTiktokOpenConfigStorage().isTicketExpired(type)) {
                    String ticket;
                    int expireInSeconds;
                    TiktokOpenAccessTokenApi accessTokenApi = new TiktokOpenAccessTokenApi();
                    switch (type) {
                        case JSAPI: {
                            TiktokOpenAccessTokenResult result = accessTokenApi.getJsapiTicketResult();
                            ticket = result.getTicket();
                            expireInSeconds = result.getExpiresIn();
                        }
                        default:
                        case CLIENT: {
                            TiktokOpenAccessTokenResult result = accessTokenApi.getClientTokenResult();
                            ticket = result.getAccessToken();
                            expireInSeconds = result.getExpiresIn();
                        }
                    }
                    this.getTiktokOpenConfigStorage().updateTicket(type, ticket, expireInSeconds);
                }
            } finally {
                lock.unlock();
            }
        }

        return this.getTiktokOpenConfigStorage().getTicket(type);
    }

    @Override
    public ITiktokOpenBaseService withAccessToken(String accessToken) {
        return this.setAccessToken(accessToken);
    }

    @Override
    public ITiktokOpenBaseService withOpenId(String openId) {
        return this.setOpenId(openId);
    }

    @Override
    public TiktokOpenJsapiSignature createJsapiSignature(String url) {
        long timestamp = System.currentTimeMillis() / 1000;
        String randomStr = SignUtil.getRandomStr();
        String jsapiTicket = getTicket(TiktokOpenTicketType.JSAPI);
        String signature = SHA1.genWithAmple("jsapi_ticket=" + jsapiTicket,
                "noncestr=" + randomStr, "timestamp=" + timestamp, "url=" + url);
        TiktokOpenJsapiSignature jsapiSignature = new TiktokOpenJsapiSignature();
        jsapiSignature.setAppId(this.getTiktokOpenConfigStorage().getAppId());
        jsapiSignature.setTimestamp(timestamp);
        jsapiSignature.setNonceStr(randomStr);
        jsapiSignature.setUrl(url);
        jsapiSignature.setSignature(signature);
        return jsapiSignature;
    }

    public String scope() {
        return null;
    };
}
