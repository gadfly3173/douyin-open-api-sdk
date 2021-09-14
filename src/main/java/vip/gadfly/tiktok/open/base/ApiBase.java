package vip.gadfly.tiktok.open.base;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.core.OkHttp3;
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
public abstract class ApiBase {

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
        String result = OkHttp3.okHttpPostJson(request.getUrl(),
                request.toJson());
        return new ApiResponse(result);
    }

    public static ApiResponse sendPost(String url, ApiRequest request) {
        String result = OkHttp3.okHttpPostJson(url, request.toJson());
        return new ApiResponse(result);
    }

    public static ApiResponse sendPost(String url, File file, String mediaType) {
        String result = OkHttp3.okHttpPost(url, file, mediaType);
        return new ApiResponse(result);
    }

    /**
     * POST 请求数据
     *
     * @param url json 内容
     * @return 返回 json 内容
     */
    public static ApiResponse sendPost(String url, String json) {
        String result = OkHttp3.okHttpPostJson(url, json);
        return new ApiResponse(result);
    }

    public static ApiResponse sendGet(String url) {
        System.out.println(url);
        String result = OkHttp3.okHttpGet(url, null);
        System.out.println(result);
        return new ApiResponse(result);
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
