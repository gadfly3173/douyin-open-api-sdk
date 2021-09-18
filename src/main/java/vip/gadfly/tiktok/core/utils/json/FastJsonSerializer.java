package vip.gadfly.tiktok.core.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeConfig;
import vip.gadfly.tiktok.core.utils.StringUtil;

import java.lang.reflect.Field;

/**
 * 问题太多，不推荐使用
 * https://segmentfault.com/a/1190000015634321
 *
 * @author yangyidian
 * @date 2020/08/03
 **/
@Deprecated
public class FastJsonSerializer implements JsonSerializer {
    private static final SerializeConfig config = new SerializeConfig();

    static {
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    @Override
    public String toJson(Object object) {
        return JSON.toJSONString(object, config);
    }

    @Override
    public <T> T parse(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    @Override
    public <T> T parseResponse(String jsonString, Class<T> clazz) {
        JSONObject responseObj = JSON.parseObject(jsonString);
        return responseObj.getObject("data", clazz);
    }

    @Override
    public String getFieldAliasName(Field field) {
        String fieldAliasName = field.getName();
        JSONField annotation = field.getAnnotation(JSONField.class);
        if (annotation != null) {
            if (!StringUtil.isBlank(annotation.name())) {
                fieldAliasName = annotation.name();
            }
        }
        return fieldAliasName;
    }
}
