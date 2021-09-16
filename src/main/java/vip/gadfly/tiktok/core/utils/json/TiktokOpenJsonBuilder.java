package vip.gadfly.tiktok.core.utils.json;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;

/**
 * @author yangyidian
 * @date 2020/08/04
 **/
@Slf4j
public class TiktokOpenJsonBuilder {
    private static final boolean GSON_PRESENT = ClassUtils.isPresent("com.google.gson.Gson",
            TiktokOpenJsonBuilder.class.getClassLoader());
    private static final boolean JACKSON_PRESENT = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper",
            TiktokOpenJsonBuilder.class.getClassLoader());
    private static final boolean FASTJSON_PRESENT = ClassUtils.isPresent("com.alibaba.fastjson.JSON",
            TiktokOpenJsonBuilder.class.getClassLoader());
    private static JsonSerializer jsonSerializer;

    static {
        if (GSON_PRESENT) {
            log.info("use gson for json serializer");
            jsonSerializer = new GsonSerializer();
        } else if (FASTJSON_PRESENT) {
            log.info("use fastjson for json serializer");
            jsonSerializer = new FastJsonSerializer();
        } else if (JACKSON_PRESENT) {
            log.info("use jackson for json serializer");
            jsonSerializer = new JacksonSerializer();
        } else {
            throw new RuntimeException("没有可用的工具将对象序列化为json");
        }
    }

    public static void setJsonSerializer(JsonSerializer jsonSerializer) {
        TiktokOpenJsonBuilder.jsonSerializer = jsonSerializer;
    }

    public static JsonSerializer instance() {
        return jsonSerializer;
    }
}
