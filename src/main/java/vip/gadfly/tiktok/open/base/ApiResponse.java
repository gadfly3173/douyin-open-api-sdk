package vip.gadfly.tiktok.open.base;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import vip.gadfly.tiktok.core.DouyinException;
import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.core.utils.StringUtil;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 请求返回
 *
 * @author OF
 * @date 2018年07月13日
 */
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 8932491316488002897L;
    public static String DATA_KEY = "data";
    private Map<String, Object> result;
    private final String json;

    public ApiResponse(String json) {
        this.json = json;
        try {
            JSONObject temp = JSONObject.parseObject(json);
            this.result = temp.getJSONObject(DATA_KEY);
        } catch (Exception e) {
            throw new DouyinException(e.getMessage() + "\n json =" + json);
        }

        if (!this.isSuccessful()) {
            throw new DouyinException(" error : this result data is null \n json =" + json);
        }
    }


    public static ApiResponse create(String json) {
        return new ApiResponse(json);
    }

    public Object get(String key) {
        return result.get(key);

    }

    public ApiResponse deep(String key) {
        Object value = result.get(key);
        if (value instanceof JSONObject) {
            this.result = (JSONObject) value;
        }
        return this;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public String getJson() {
        return json;
    }

    public String getStr(String name) {
        return (String) result.get(name);
    }

    public Integer getInt(String name) {
        Number number = (Number) result.get(name);
        return number == null ? null : number.intValue();
    }

    public Long getLong(String name) {
        Number number = (Number) result.get(name);
        return number == null ? null : number.longValue();
    }


    public boolean isSuccessful() {
        if (StringUtil.isEmpty(this.result)) {
            return false;
        }
        Integer errorCode = (Integer) this.result.get("error_code");
        return (errorCode != null && errorCode == 0);
    }

    public Integer getErrorCode() {
        return (Integer) this.result.get("error_code");
    }

    public String getErrorMsg() {
        Integer errorCode = getErrorCode();
        if (errorCode != null) {
            return ApiErrorCode.getErrorCode(errorCode);
        }
        return (String) result.get("message");
    }

    /**
     * 1. 若access_token已过期，调用接口会报错(error_code=10008或2190008)，refresh_token后会获取一个新的access_token以及新的超时时间。
     * 2. 若access_token未过期，refresh_token不会改变原来的access_token，但超时时间会更新，相当于续期。
     * 3. 若refresh_token过期，获取access_token会报错(error_code=10010)，此时需要重新走用户授权流程。
     *
     * @return {boolean}
     */
    public boolean isAccessTokenInvalid() {
        Integer ec = getErrorCode();
        return ec != null
                       && (ec == 10008 || ec == 2190008 || ec == 10010);
    }


    /**
     * 将json转换成bean对象
     *
     * @param cl
     * @return
     */
    public <T> T dataToBean(Class<?> cl) {
        T obj;
        try {
            String data = JsonUtil.objectToJson(this.result);
            obj = (T) JsonUtil.jsonToBean(data, cl);
        } catch (Exception ex) {
            throw new DouyinException(" error : data josn to bean is error" + ex.getMessage());
        }
        return obj;
    }

    /**
     * 将json转换成bean集合
     *
     * @return
     */
    public <T> List<T> dataToList() {
        List<T> list;
        try {
            Type type = new TypeToken<List<T>>() {
            }.getType();
            list = (List<T>) JsonUtil.jsonToList(JsonUtil.objectToJson(this.result), type);

        } catch (Exception ex) {
            throw new DouyinException(" error : data josn to bean is error" + ex.getMessage());
        }
        return list;
    }


    public void set(String key, Object value) {
        result.put(key, value);
    }
}
