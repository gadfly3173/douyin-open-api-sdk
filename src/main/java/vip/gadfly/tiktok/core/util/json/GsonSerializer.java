package vip.gadfly.tiktok.core.util.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.core.util.StringUtil;

import java.lang.reflect.Field;

/**
 * @author yangyidian
 * @date 2020/08/03
 **/
public class GsonSerializer implements JsonSerializer {
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T parse(String jsonString, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) jsonString;
        }
        return gson.fromJson(jsonString, clazz);
    }

    @Override
    public <T> T parseResponse(String jsonString, Class<T> clazz) {
        JsonObject responseObj = gson.fromJson(jsonString, JsonObject.class);
        return gson.fromJson(responseObj.get("data"), clazz);
    }

    @Override
    public String getFieldAliasName(Field field) {
        String fieldAliasName = field.getName();
        SerializedName annotation = field.getAnnotation(SerializedName.class);
        if (annotation != null) {
            if (!StringUtil.isBlank(annotation.value())) {
                fieldAliasName = annotation.value();
            }
        }
        return fieldAliasName;
    }
}
