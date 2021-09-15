package vip.gadfly.tiktok.core.utils;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Gson类库的封装工具类，专门负责解析json数据</br> 内部实现了Gson对象的单例
 *
 * @author OF
 * @version 1.0
 * @since 2012-9-18
 */
public class JsonUtil {

    private static Gson gson = new Gson();

    /**
     * 将对象转换成json格式
     *
     * @param ts
     * @return
     */
    public static String objectToJson(Object ts) {
        String jsonStr = null;
        if (gson != null) {
            jsonStr = gson.toJson(ts);
        }
        return jsonStr;
    }

    /**
     * 将对象转换成json格式空值保留
     *
     * @param ts
     * @return
     */
    public static String objectToJsonNull(Object ts) {
        Gson g = new GsonBuilder().serializeNulls().create();
        String jsonStr = "";
        jsonStr = g.toJson(ts);
        return jsonStr;
    }

    /**
     * 将对象转换成json格式(并自定义日期格式)
     *
     * @param ts
     * @return
     */
    public static String objectToJsonDateSerializer(Object ts,
                                                    final String dateformat) {
        String jsonStr;
        gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Date.class,
                        (JsonSerializer<Date>) (src, typeOfSrc, context) -> {
                            SimpleDateFormat format = new SimpleDateFormat(
                                    dateformat);
                            return new JsonPrimitive(format.format(src));
                        }).setDateFormat(dateformat).create();
        jsonStr = gson.toJson(ts);
        return jsonStr;
    }

    /**
     * 将json格式转换成list对象
     *
     * @param jsonStr
     * @return
     */
    public static List<?> jsonToList(String jsonStr) {
        List<?> objList = null;
        if (gson != null) {
            Type type = new TypeToken<List<?>>() {
            }.getType();
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * 将json格式转换成list对象，并准确指定类型
     *
     * @param jsonStr
     * @param type
     * @return
     */
    public static <T> List<T> jsonToList(String jsonStr, Type type) {
        List<T> objList = null;
        if (gson != null) {
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * 将json格式转换成map对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        Map<?, ?> objMap = null;
        if (gson != null) {
            Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @return
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> cl) {
        T obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return obj;
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @param cl
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl,
                                                 final String pattern) {
        T obj;
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> {
                    SimpleDateFormat format = new SimpleDateFormat(pattern);
                    String dateStr = json.getAsString();
                    try {
                        return format.parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).setDateFormat(pattern).create();
        obj = gson.fromJson(jsonStr, cl);
        return obj;
    }

    /**
     * 根据
     *
     * @param jsonStr
     * @param key
     * @return
     */
    public static Object getJsonValue(String jsonStr, String key) {
        Object rulsObj = null;
        Map<?, ?> rulsMap = jsonToMap(jsonStr);
        if (rulsMap != null && rulsMap.size() > 0) {
            rulsObj = rulsMap.get(key);
        }
        return rulsObj;
    }

    @SuppressWarnings({"unused", "unchecked"})
    private static Map<String, Object> treeListToArrayMap(
            List<Map<String, Object>> dataList, String id, String childNode,
            Map<String, Object> dataMap) {
        if (dataMap == null) {
            dataMap = new LinkedTreeMap<String, Object>();
        }
        for (Map<String, Object> map : dataList) {
            dataMap.put((String) map.get(id), map);
            // 是否有子节点
            Object o = map.get(childNode);
            if (o instanceof List) {
                treeListToArrayMap((List<Map<String, Object>>) o, id,
                        childNode, dataMap);
            }
        }
        return dataMap;
    }

    @SuppressWarnings("unused")
    private static Map<String, Object> treeListToArrayMapWithOutChild(
            List<Map<String, Object>> dataList, String id,
            Map<String, Object> dataMap) {
        if (dataMap == null) {
            dataMap = new LinkedTreeMap<>();
        }
        for (Map<String, Object> map : dataList) {
            dataMap.put((String) map.get(id), map);
        }
        return dataMap;
    }

}