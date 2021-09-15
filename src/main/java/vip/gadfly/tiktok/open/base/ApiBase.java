package vip.gadfly.tiktok.open.base;

import lombok.extern.slf4j.Slf4j;
import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.core.OkHttp3;
import vip.gadfly.tiktok.core.exception.TikTokException;
import vip.gadfly.tiktok.core.utils.StringUtil;
import vip.gadfly.tiktok.open.api.token.AccessTokenConfig;

import java.io.File;

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
public abstract class ApiBase {

    public final static int tiktokBusyCode = 2100004;
    private final static int retrySleepMillis = 1000;
    private final static int maxRetryTimes = 5;
    private String openId;
    private String appId = AppConfig.getAppId();
    private String accessToken;

    public ApiBase(String openId) {
        this.openId = openId;
    }

    public ApiBase() {
    }

    /**
     * POST 请求数据
     *
     * @param request json 内容
     * @return 返回 ApiResponse 内容
     */
    public static ApiResponse sendPost(ApiRequest request) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp3.okHttpPostJson(request.getUrl(),
                        request.toJson());
                return new ApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public static ApiResponse sendPost(String url, ApiRequest request) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp3.okHttpPostJson(url, request.toJson());
                return new ApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public static ApiResponse sendPost(String url, File file, String mediaType) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp3.okHttpPost(url, file, mediaType);
                return new ApiResponse(result);
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
    public static ApiResponse sendPost(String url, String json) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp3.okHttpPostJson(url, json);
                return new ApiResponse(result);
            } catch (TikTokException e) {
                responseExceptionHandler(retryTimes, e);
            }
        } while (retryTimes++ < maxRetryTimes);
        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new TikTokException("抖音服务端异常，超出重试次数");
    }

    public static ApiResponse sendGet(String url) {
        int retryTimes = 0;
        do {
            try {
                String result = OkHttp3.okHttpGet(url, null);
                return new ApiResponse(result);
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
        if (e.getErrorCode() != tiktokBusyCode) {
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

    public String getOpenId() {
        return openId;
    }

    public ApiBase setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public ApiBase setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    /**
     * 缓存本地的 key (openId / appId)
     *
     * @return
     */
    public String getCacheKey() {
        if (!StringUtil.isEmpty(getOpenId())) {
            return getOpenId();
        } else {
            return getAppId();
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
        AccessTokenConfig config = new AccessTokenConfig();
        return config.getAccessToken(getCacheKey(), true);
    }

    public ApiBase setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public abstract ApiBase withAccessToken(String accessToken);

    public abstract ApiBase withOpenId(String openId);

    public abstract String scope();
}
