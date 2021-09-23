package vip.gadfly.tiktok.core.http.impl;

import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import vip.gadfly.tiktok.core.exception.TtOpError;
import vip.gadfly.tiktok.core.exception.TtOpErrorException;
import vip.gadfly.tiktok.core.http.ITtOpHttpClient;
import vip.gadfly.tiktok.core.http.ITtOpResponse;
import vip.gadfly.tiktok.core.util.json.JsonSerializer;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangyidian
 * @date 2020/08/04
 **/
@Slf4j
public abstract class AbstractTtOpHttpClient implements ITtOpHttpClient {

    private final JsonSerializer jsonSerializer;

    public AbstractTtOpHttpClient(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public JsonSerializer getJsonSerializer() {
        return jsonSerializer;
    }

    @Override
    public <T> T get(String url, Class<T> clazz) {
        log.info("get请求字节跳动接口,请求地址: 【{}】", url);
        T response = doGet(url, clazz);
        if (response instanceof ITtOpResponse) {
            log.info("请求字节跳动接口返回数据: 【{}】", getJsonSerializer().toJson(response));
        } else {
            log.info("请求字节跳动接口返回数据, 类型:【{}】, 内容:【{}】", clazz.getTypeName(), response);
        }
        return handleResponse(response);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> t) {
        log.info("post请求字节跳动接口, 请求地址【{}】, 参数【{}】", url, getJsonSerializer().toJson(request));
        T response = doPost(url, request, t);
        if (response instanceof ITtOpResponse) {
            log.info("请求字节跳动接口返回数据: 【{}】", getJsonSerializer().toJson(response));
        } else {
            log.info("请求字节跳动接口返回数据, 类型:【{}】, 内容:【{}】", t.getTypeName(), response);
        }
        return handleResponse(response);
    }

    @Override
    public <T> T postWithHeaders(String url, Multimap<String, String> headers, Object request, Class<T> t) {
        log.info("post请求字节跳动接口, 请求地址【{}】, 参数【{}】", url, getJsonSerializer().toJson(request));
        T response = doPostWithHeaders(url, headers, request, t);
        if (response instanceof ITtOpResponse) {
            log.info("请求字节跳动接口返回数据: 【{}】", getJsonSerializer().toJson(response));
        } else {
            log.info("请求字节跳动接口返回数据, 类型:【{}】, 内容:【{}】", t.getTypeName(), response);
        }
        return handleResponse(response);
    }


    private <T> T handleResponse(T response) {
        if (response instanceof TtOpBaseResult) {
            checkError((TtOpBaseResult) response);
        } else if (response instanceof TtOpError) {
            checkError((TtOpError) response);
        } else if (response instanceof byte[]) {
            try {
                checkError(new String((byte[]) response));
            } catch (RuntimeException e) {
                if (e instanceof TtOpErrorException) {
                    throw e;
                }  // do nothing
            }
        }
        return response;
    }

    private void checkError(String response) {
        TtOpError error = getJsonSerializer().parseResponse(response, TtOpError.class);
        checkError(error);
    }

    private void checkError(TtOpError error) {
        if (error.getErrorCode() != null && error.getErrorCode() != 0) {
            log.error("字节跳动接口返回异常：{}", error.getErrorCode());
            throw new TtOpErrorException(error);
        }
    }

    private void checkError(TtOpBaseResult result) {
        if (result.getErrorCode() != null && result.getErrorCode() != 0) {
            log.error("字节跳动接口返回异常：{}", result.getErrorCode());
            throw new TtOpErrorException(result);
        }
    }

    String doGet(String url) {
        return doGet(url, String.class);
    }

    abstract <T> T doGet(String url, Class<T> t);


    String doPost(String url, Object obj) {
        return doPost(url, obj, String.class);
    }

    abstract <T> T doPost(String url, Object request, Class<T> t);


    abstract <T> T doPostWithHeaders(String url, Multimap<String, String> headers, Object requestParam, Class<T> t);

    /**
     * 有些参数的值要特殊处理，比如用Resttemplate上传文件时，File要转成FileSystemResource
     *
     * @param requestParams
     * @return
     */
    Object handlerRequestParam(Object requestParams) {
        Field[] fields = requestParams.getClass().getDeclaredFields();
        Map<String, Object> paramsMap = new HashMap<>(fields.length);
        for (Field field : fields) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(requestParams);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            String aliasName = getJsonSerializer().getFieldAliasName(field);
            paramsMap.put(aliasName, value);
        }
        return paramsMap;
    }

    Map<String, String> multimapHeaders2MapHeaders(Multimap<String, String> headers) {
        Map<String, String> headerMap = new HashMap<>();
        StringBuilder stringBuilder;
        for (String key : headers.keySet()) {
            stringBuilder = new StringBuilder();
            for (String value : headers.get(key)) {
                stringBuilder.append(value).append(";");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            headerMap.put(key, stringBuilder.toString());
        }
        return headerMap;
    }
}
