package vip.gadfly.tiktok.open.base;


import vip.gadfly.tiktok.core.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class TiktokOpenApiRequest {
    private Map<String, Object> body = new HashMap<String, Object>();
    private String url;

    public TiktokOpenApiRequest() {
    }

    public TiktokOpenApiRequest(Map<String, Object> body) {
        this.body = body;
    }

    public static TiktokOpenApiRequest create(String key, Object value) {
        return create().set(key, value);
    }

    public static TiktokOpenApiRequest create() {
        return new TiktokOpenApiRequest();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public String toJson() {
        return JsonUtil.objectToJson(body);
    }

    public TiktokOpenApiRequest set(String key, Object value) {
        body.put(key, value);
        return this;
    }

    public TiktokOpenApiRequest set(String key, Map<?, ?> map) {
        body.put(key, map);
        return this;
    }

    public TiktokOpenApiRequest set(String key, TiktokOpenApiRequest value) {
        body.put(key, value.getBody());
        return this;
    }

}
