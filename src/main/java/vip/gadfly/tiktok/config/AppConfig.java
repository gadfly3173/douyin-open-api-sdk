package vip.gadfly.tiktok.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共配置文件(单例)
 *
 * @author OF
 * @date 2017年10月10日
 */
public class AppConfig {
    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
    private static final AppConfig appConfig = new AppConfig();
    /**
     * 应用唯一标识
     */
    public String appId;
    /**
     * 密钥
     */
    public String appSecret;
    /**
     * 超时时间
     */
    public String appTimeout = "3000";
    /**
     * 默认配置文件路径
     */
    public String configFile = "douyin.properties";
    /**
     * 默认API路径
     */
    public String httpUrl = "https://open.douyin.com";
    /**
     * code回调路径
     */
    public String redirectUri;

    public AppConfig() {
    }

    public static AppConfig getInstance() {
        return appConfig;
    }

    public static String getAppId() {
        return appConfig.appId;
    }

    public void setAppId(String appId) {
        appConfig.appId = appId;
    }

    public static AppConfig getAppConfig() {
        return appConfig;
    }

    public String getAppSecret() {
        return appConfig.appSecret;
    }

    public void setAppSecret(String appSecret) {
        appConfig.appSecret = appSecret;
    }

    public String getAppTimeout() {
        return appConfig.appTimeout;
    }

    public void setAppTimeout(String appTimeout) {
        appConfig.appTimeout = appTimeout;
    }

    public String getHttpUrl() {
        return appConfig.httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        appConfig.httpUrl = httpUrl;
    }

    public String getRedirectUri() {
        return appConfig.redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        appConfig.redirectUri = redirectUri;
    }

    /**
     * 设置配置文件
     *
     * @param appId
     * @param appSecret
     * @param appTimeout
     * @param appCharset
     * @return
     */
    public AppConfig setConfig(String appId, String appSecret,
                               String appTimeout, String appCharset, String httpUrl, String redirectUri) {
        appConfig.appId = appId;
        appConfig.appSecret = appSecret;
        appConfig.appTimeout = appTimeout;
        appConfig.httpUrl = httpUrl;
        appConfig.redirectUri = redirectUri;
        return this;
    }

    public AppConfig setConfig(String appId, String appSecret, String redirectUri) {
        appConfig.appId = appId;
        appConfig.appSecret = appSecret;
        appConfig.redirectUri = redirectUri;
        return this;
    }

}
