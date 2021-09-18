package vip.gadfly.tiktok.open.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.core.exception.ITtOpError;
import vip.gadfly.tiktok.core.exception.TikTokException;
import vip.gadfly.tiktok.core.exception.TtOpErrorException;
import vip.gadfly.tiktok.core.exception.TtOpErrorMsgEnum;
import vip.gadfly.tiktok.core.http.ITtOpHttpClient;
import vip.gadfly.tiktok.core.http.OkHttp4;
import vip.gadfly.tiktok.core.http.impl.OkHttpTtOpHttpClient;
import vip.gadfly.tiktok.core.utils.StringUtil;
import vip.gadfly.tiktok.core.utils.TtOpConfigStorageHolder;
import vip.gadfly.tiktok.core.utils.crypto.SHA1;
import vip.gadfly.tiktok.core.utils.crypto.SignUtil;
import vip.gadfly.tiktok.open.api.TtOpOAuth2Service;
import vip.gadfly.tiktok.open.api.impl.TtOpOauth2ServiceImpl;
import vip.gadfly.tiktok.open.api.token.TtOpAccessTokenApi;
import vip.gadfly.tiktok.open.api.token.TtOpAccessTokenResult;
import vip.gadfly.tiktok.open.common.bean.TtOpJsapiSignature;

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
public abstract class AbstractTtOpApiBase implements ITtOpBaseService, IRetryableExecutor {

    private static final String ACCESS_TOKEN_PREFIX = "{0}:{1}:access_token";
    private int retrySleepMillis = 1000;
    private int maxRetryTimes = 5;
    private String openId;
    @Getter
    @Setter
    private ITtOpHttpClient tiktokOpenHttpClient = new OkHttpTtOpHttpClient();
    @Getter
    @Setter
    private TtOpOAuth2Service ttOpOAuth2Service = new TtOpOauth2ServiceImpl(this);

    private Map<String, TtOpConfigStorage> configStorageMap;

    public AbstractTtOpApiBase(String openId) {
        this.openId = openId;
    }

    public AbstractTtOpApiBase() {
    }

    /**
     * POST 请求数据
     *
     * @param request json 内容
     * @return 返回 TtOpApiResponse 内容
     */
    public TtOpApiResponse sendPost(TtOpApiRequest request) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPostJson(request.getUrl(),
                        request.toJson());
                return new TtOpApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < this.maxRetryTimes);
        log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public TtOpApiResponse sendPost(String url, TtOpApiRequest request) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPostJson(url, request.toJson());
                return new TtOpApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public TtOpApiResponse sendPost(String url, File file, String mediaType) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPost(url, file, mediaType);
                return new TtOpApiResponse(result);
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
    public TtOpApiResponse sendPost(String url, String json) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpPostJson(url, json);
                return new TtOpApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public TtOpApiResponse sendGet(String url) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp4.okHttpGet(url, null);
                return new TtOpApiResponse(result);
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
        if (e.getErrorCode() != TtOpApiResponse.TIKTOK_OPEN_BUSY_CODE) {
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
    public boolean shouldRetry(ITtOpError error) {
        return TtOpErrorMsgEnum.CODE_2100004.getCode() == error.getErrorCode();
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
        } catch (TtOpErrorException e) {
            ITtOpError error = e.getError();
            if (!error.checkSuccess()) {
                log.error("\n【请求地址】: {}\n【错误信息】: {}", url, e.getError());
                throw new TtOpErrorException(error, e);
            }
            return null;
        }
    }

    @Override
    public TtOpConfigStorage getTtOpConfigStorage() {
        if (this.configStorageMap.size() == 1) {
            // 只有一个公众号，直接返回其配置即可
            return this.configStorageMap.values().iterator().next();
        }

        return this.configStorageMap.get(TtOpConfigStorageHolder.get());
    }

    @Override
    public void setTiktokOpenConfigStorage(TtOpConfigStorage tiktokConfigProvider) {
        final String defaultMpId = tiktokConfigProvider.getAppId();
        this.setMultiConfigStorages(ImmutableMap.of(defaultMpId, tiktokConfigProvider), defaultMpId);
    }

    @Override
    public void setMultiConfigStorages(Map<String, TtOpConfigStorage> configStorages) {
        this.setMultiConfigStorages(configStorages, configStorages.keySet().iterator().next());
    }

    @Override
    public void setMultiConfigStorages(Map<String, TtOpConfigStorage> configStorages, String defaultAppId) {
        this.configStorageMap = Maps.newHashMap(configStorages);
        TtOpConfigStorageHolder.set(defaultAppId);
    }

    @Override
    public void addConfigStorage(String appId, TtOpConfigStorage configStorages) {
        synchronized (this) {
            if (this.configStorageMap == null) {
                this.setTiktokOpenConfigStorage(configStorages);
            } else {
                TtOpConfigStorageHolder.set(appId);
                this.configStorageMap.put(appId, configStorages);
            }
        }
    }

    @Override
    public void removeConfigStorage(String appId) {
        synchronized (this) {
            if (this.configStorageMap.size() == 1) {
                this.configStorageMap.remove(appId);
                log.warn("已删除最后一个公众号配置：{}，须立即使用setTiktokOpenConfigStorage或setMultiConfigStorages添加配置", appId);
                return;
            }
            if (TtOpConfigStorageHolder.get().equals(appId)) {
                this.configStorageMap.remove(appId);
                final String defaultMpId = this.configStorageMap.keySet().iterator().next();
                TtOpConfigStorageHolder.set(defaultMpId);
                log.warn("已删除默认公众号配置，公众号【{}】被设为默认配置", defaultMpId);
                return;
            }
            this.configStorageMap.remove(appId);
        }
    }

    @Override
    public boolean switchover(String appId) {
        if (this.configStorageMap.containsKey(appId)) {
            TtOpConfigStorageHolder.set(appId);
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
    public ITtOpBaseService setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    @Override
    public String getAppId() {
        TtOpConfigStorage configStorage = getTtOpConfigStorage();
        return configStorage.getAppId();
    }

    @Override
    public String getHttpUrl() {
        TtOpConfigStorage configStorage = getTtOpConfigStorage();
        return configStorage.getHostConfig().getTiktokOpenHost();
    }

    /**
     * 缓存本地的 key (openId / appId)
     *
     * @return key
     */
    public String getCacheKey() {
        if (!StringUtil.isEmpty(getOpenId())) {
            return MessageFormat.format(ACCESS_TOKEN_PREFIX, getAppId(), getOpenId());
        } else {
            return MessageFormat.format(ACCESS_TOKEN_PREFIX, getAppId(), "");
        }
    }

    @Override
    public String getAccessToken(String openid) {
        return this.getAccessToken(openid, false);
    }

    @Override
    public String getAccessToken(String openid, boolean isRefresh) {
        return this.getTtOpConfigStorage().getAccessToken(openid);
    }

    @Override
    public String getTicket(TtOpTicketType type) {
        return this.getTicket(type, false);
    }

    @Override
    public String getTicket(TtOpTicketType type, boolean forceRefresh) {
        if (forceRefresh) {
            this.getTtOpConfigStorage().expireTicket(type);
        }

        if (this.getTtOpConfigStorage().isTicketExpired(type)) {
            Lock lock = this.getTtOpConfigStorage().getTicketLock(type);
            lock.lock();
            try {
                if (this.getTtOpConfigStorage().isTicketExpired(type)) {
                    switch (type) {
                        case JSAPI: {
                            this.getTtOpOAuth2Service().getJsapiTicket(true);
                            break;
                        }
                        default:
                        case CLIENT: {
                            this.getTtOpOAuth2Service().getClientToken(true);
                            break;
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        return this.getTtOpConfigStorage().getTicket(type);
    }

    @Override
    public TtOpJsapiSignature createJsapiSignature(String url) {
        long timestamp = System.currentTimeMillis() / 1000;
        String randomStr = SignUtil.getRandomStr();
        String jsapiTicket = getTicket(TtOpTicketType.JSAPI);
        String signature = SHA1.genWithAmple("jsapi_ticket=" + jsapiTicket,
                "noncestr=" + randomStr, "timestamp=" + timestamp, "url=" + url);
        TtOpJsapiSignature jsapiSignature = new TtOpJsapiSignature();
        jsapiSignature.setAppId(this.getTtOpConfigStorage().getAppId());
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
