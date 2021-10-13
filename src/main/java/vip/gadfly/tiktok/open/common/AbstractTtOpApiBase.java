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
import vip.gadfly.tiktok.core.exception.TtOpErrorException;
import vip.gadfly.tiktok.core.exception.TtOpErrorMsgEnum;
import vip.gadfly.tiktok.core.http.ITtOpHttpClient;
import vip.gadfly.tiktok.core.http.impl.OkHttpTtOpHttpClient;
import vip.gadfly.tiktok.core.util.TtOpConfigStorageHolder;
import vip.gadfly.tiktok.core.util.crypto.MD5;
import vip.gadfly.tiktok.core.util.crypto.SignUtil;
import vip.gadfly.tiktok.open.api.TtOpOAuth2Service;
import vip.gadfly.tiktok.open.api.TtOpUserInfoService;
import vip.gadfly.tiktok.open.api.TtOpVideoService;
import vip.gadfly.tiktok.open.api.impl.TtOpOauth2ServiceImpl;
import vip.gadfly.tiktok.open.api.impl.TtOpUserInfoServiceImpl;
import vip.gadfly.tiktok.open.api.impl.TtOpVideoServiceImpl;
import vip.gadfly.tiktok.open.bean.oauth2.TtOpJsapiSignature;

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
 * @author Gadfly
 * @since 2018年7月13日
 */
@Slf4j
public abstract class AbstractTtOpApiBase implements ITtOpBaseService, IRetryableExecutor {

    @Getter
    @Setter
    private int retrySleepMillis = 1000;
    @Getter
    @Setter
    private int maxRetryTimes = 5;
    @Getter
    @Setter
    private ITtOpHttpClient tiktokOpenHttpClient = new OkHttpTtOpHttpClient();
    @Getter
    @Setter
    private TtOpOAuth2Service ttOpOAuth2Service = new TtOpOauth2ServiceImpl(this);
    @Getter
    @Setter
    private TtOpUserInfoService ttOpUserInfoService = new TtOpUserInfoServiceImpl(this);
    @Getter
    @Setter
    private TtOpVideoService ttOpVideoService = new TtOpVideoServiceImpl(this);

    private Map<String, TtOpConfigStorage> configStorageMap;

    public AbstractTtOpApiBase() {
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
            // 只有一个抖音开发者应用，直接返回其配置即可
            return this.configStorageMap.values().iterator().next();
        }

        return this.configStorageMap.get(TtOpConfigStorageHolder.get());
    }

    @Override
    public void setTiktokOpenConfigStorage(TtOpConfigStorage tiktokConfigProvider) {
        final String defaultMpId = tiktokConfigProvider.getClientKey();
        this.setMultiConfigStorages(ImmutableMap.of(defaultMpId, tiktokConfigProvider), defaultMpId);
    }

    @Override
    public void setMultiConfigStorages(Map<String, TtOpConfigStorage> configStorages) {
        this.setMultiConfigStorages(configStorages, configStorages.keySet().iterator().next());
    }

    @Override
    public void setMultiConfigStorages(Map<String, TtOpConfigStorage> configStorages, String defaultClientKey) {
        this.configStorageMap = Maps.newHashMap(configStorages);
        TtOpConfigStorageHolder.set(defaultClientKey);
    }

    @Override
    public void addConfigStorage(String clientKey, TtOpConfigStorage configStorages) {
        synchronized (this) {
            if (this.configStorageMap == null) {
                this.setTiktokOpenConfigStorage(configStorages);
            } else {
                TtOpConfigStorageHolder.set(clientKey);
                this.configStorageMap.put(clientKey, configStorages);
            }
        }
    }

    @Override
    public void removeConfigStorage(String clientKey) {
        synchronized (this) {
            if (this.configStorageMap.size() == 1) {
                this.configStorageMap.remove(clientKey);
                log.warn("已删除最后一个抖音开发者应用配置：{}，须立即使用setTiktokOpenConfigStorage或setMultiConfigStorages添加配置", clientKey);
                return;
            }
            if (TtOpConfigStorageHolder.get().equals(clientKey)) {
                this.configStorageMap.remove(clientKey);
                final String defaultMpId = this.configStorageMap.keySet().iterator().next();
                TtOpConfigStorageHolder.set(defaultMpId);
                log.warn("已删除默认抖音开发者应用配置，抖音开发者应用【{}】被设为默认配置", defaultMpId);
                return;
            }
            this.configStorageMap.remove(clientKey);
        }
    }

    @Override
    public boolean switchover(String clientKey) {
        if (this.configStorageMap.containsKey(clientKey)) {
            TtOpConfigStorageHolder.set(clientKey);
            return true;
        }

        log.error("无法找到对应【{}】的抖音开发者应用配置信息，请核实！", clientKey);
        return false;
    }

    @Override
    public String getClientKey() {
        TtOpConfigStorage configStorage = getTtOpConfigStorage();
        return configStorage.getClientKey();
    }

    @Override
    public String getHttpUrl() {
        TtOpConfigStorage configStorage = getTtOpConfigStorage();
        return configStorage.getHostConfig().getTiktokOpenHost();
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
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String randomStr = SignUtil.getRandomStr();
        String jsapiTicket = getTicket(TtOpTicketType.JSAPI);
        String signature = MD5.genWithAmple("jsapi_ticket=" + jsapiTicket,
                "nonce_str=" + randomStr, "timestamp=" + timestamp, "url=" + url);
        return TtOpJsapiSignature.builder()
                .clientKey(this.getTtOpConfigStorage().getClientKey())
                .timestamp(timestamp)
                .nonceStr(randomStr)
                .url(url)
                .signature(signature)
                .build();
    }

    @Override
    public boolean checkWebhookSignature(String xSignature, String body) {
        String clientSecret = this.getTtOpConfigStorage().getClientSecret();
        return SignUtil.checkWebhookSignature(xSignature, clientSecret, body);
    }
}
