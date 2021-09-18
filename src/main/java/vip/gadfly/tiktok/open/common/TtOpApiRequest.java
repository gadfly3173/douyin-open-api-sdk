package vip.gadfly.tiktok.open.common;


import vip.gadfly.tiktok.core.http.ITtOpRequest;
import vip.gadfly.tiktok.core.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class TtOpApiRequest implements ITtOpRequest {
    private Map<String, Object> body = new HashMap<String, Object>();
    private String url;

    public TtOpApiRequest() {
    }

    public TtOpApiRequest(Map<String, Object> body) {
        this.body = body;
    }

    public static TtOpApiRequest create(String key, Object value) {
        return create().set(key, value);
    }

    public static TtOpApiRequest create() {
        return new TtOpApiRequest();
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

    public TtOpApiRequest set(String key, Object value) {
        body.put(key, value);
        return this;
    }

    public TtOpApiRequest set(String key, Map<?, ?> map) {
        body.put(key, map);
        return this;
    }

    public TtOpApiRequest set(String key, TtOpApiRequest value) {
        body.put(key, value.getBody());
        return this;
    }

}
