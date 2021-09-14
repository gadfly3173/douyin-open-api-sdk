package vip.gadfly.tiktok.open.base;


import vip.gadfly.tiktok.core.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    private Map<String, Object> body = new HashMap<String, Object>();
    private String url;

    public ApiRequest() {
    }

    public ApiRequest(Map<String, Object> body) {
        this.body = body;
    }

    public static ApiRequest create(String key, Object value) {
        return create().set(key, value);
    }

    public static ApiRequest create() {
        return new ApiRequest();
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

    public ApiRequest set(String key, Object value) {
        body.put(key, value);
        return this;
    }

    public ApiRequest set(String key, Map<?, ?> map) {
        body.put(key, map);
        return this;
    }

    public ApiRequest set(String key, ApiRequest value) {
        body.put(key, value.getBody());
        return this;
    }

}
