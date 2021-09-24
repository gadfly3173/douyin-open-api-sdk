package vip.gadfly.tiktok.core.util.json;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.Getter;
import vip.gadfly.tiktok.core.util.StringUtil;
import vip.gadfly.tiktok.open.common.TtOpBaseResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 使用jackson需要版本 >= 2.12
 * <p>
 * jackson的驼峰转下划线策略有点奇怪
 * aBcDEFgH会被转为abc_defg_h,所以最好都通过JsonProperty注解明确指定属性对应的json别名
 *
 * @author yangyidian
 * @date 2020/08/03
 **/
public class JacksonSerializer implements JsonSerializer {

    @Getter
    private static final ObjectMapper mapper = new ObjectMapper();

    public JacksonSerializer() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public String toJson(Object object) {
        String jsonStr;
        try {
            jsonStr = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T parse(String jsonString, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) jsonString;
        }
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T parseResponse(String jsonString, Class<T> clazz) {
        try {
            JavaType type = mapper.getTypeFactory().constructParametricType(TtOpBaseResponse.class, clazz);
            TtOpBaseResponse<T> response = mapper.readValue(jsonString, type);
            return response.getData();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFieldAliasName(Field field) {
        String fieldAliasName = field.getName();
        Annotation annotation = field.getAnnotation(JsonAlias.class);
        if (annotation != null) {
            JsonAlias jsonAnnotation = (JsonAlias) annotation;
            if (jsonAnnotation.value().length > 0 && !StringUtil.isBlank(jsonAnnotation.value()[0])) {
                fieldAliasName = jsonAnnotation.value()[0];
            }
        } else {
            annotation = field.getAnnotation(JsonProperty.class);
            if (annotation != null) {
                JsonProperty jsonAnnotation = (JsonProperty) annotation;
                if (!StringUtil.isBlank(jsonAnnotation.value())) {
                    fieldAliasName = jsonAnnotation.value();
                }
            }
        }
        return fieldAliasName;
    }
}
