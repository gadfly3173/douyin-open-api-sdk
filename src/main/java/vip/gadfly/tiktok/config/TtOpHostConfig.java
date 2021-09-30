package vip.gadfly.tiktok.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信接口地址域名部分的自定义设置信息.
 *
 * @author Gadfly
 * @since 2021-09-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TtOpHostConfig {
    public static final String TIKTOK_OPEN_API_HOST_URL = "https://open.douyin.com";
    public static final String TOUTIAO_OPEN_API_HOST_URL = "https://open.snssdk.com";
    public static final String XIGUA_OPEN_API_HOST_URL = "https://open-api.ixigua.com";
    public static final String TIKTOK_SILENT_OPEN_API_HOST_URL = "https://aweme.snssdk.com";

    /**
     * 对应于：https://open.douyin.com
     */
    private String tiktokOpenHost;

    /**
     * 对应于：https://open.snssdk.com
     */
    private String toutiaoOpenHost;

    /**
     * 对应于：https://open-api.ixigua.com
     */
    private String xiguaOpenHost;

    public static String buildUrl(TtOpHostConfig hostConfig, String prefix, String path) {
        if (hostConfig == null) {
            return prefix + path;
        }

        if (hostConfig.getTiktokOpenHost() != null && prefix.equals(TIKTOK_OPEN_API_HOST_URL)) {
            return hostConfig.getTiktokOpenHost() + path;
        }

        if (hostConfig.getToutiaoOpenHost() != null && prefix.equals(TOUTIAO_OPEN_API_HOST_URL)) {
            return hostConfig.getToutiaoOpenHost() + path;
        }

        if (hostConfig.getXiguaOpenHost() != null && prefix.equals(XIGUA_OPEN_API_HOST_URL)) {
            return hostConfig.getXiguaOpenHost() + path;
        }

        return prefix + path;
    }
}
