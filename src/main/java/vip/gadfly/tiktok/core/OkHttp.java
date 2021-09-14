package vip.gadfly.tiktok.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

public class OkHttp {
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
            .parse("text/x-markdown; charset=utf-8");
    public static final MediaType js = MediaType
            .parse("application/json; charset=utf-8");
    private static final Logger log = LoggerFactory.getLogger(OkHttp.class);
    public static OkHttpClient httpClinet = null;

    static {
        httpClinet = new OkHttpClient();
        // httpClinet.interceptors().add(new LoggingInterceptor());
        httpClinet.setConnectTimeout(1000 * 30, TimeUnit.MILLISECONDS);
        /*
         * httpClinet.setSslSocketFactory(getUnsafeOkHttpClient());
         * httpClinet.setHostnameVerifier(new HostnameVerifier() {
         *
         * @Override public boolean verify(String hostname, SSLSession session)
         * { return true; } });
         */

        /*
         * httpClinet.setCertificatePinner(new CertificatePinner.Builder()
         * .add("publicobject.com", "sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=")
         * .add("publicobject.com", "sha1/SXxoaOSEzPC6BgGmxAt/EAcsajw=")
         * .add("publicobject.com", "sha1/blhOM3W9V/bVQhsWAcLYwPU6n24=")
         * .add("publicobject.com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
         * .build());
         */
    }

    /**
     * https post json 请求方法
     *
     * @param url 请求地址
     * @return string json
     */
    public static String okHttpsPostJson(String url, String content) {
        httpClinet.setSslSocketFactory(getUnsafeOkHttpClient());
        httpClinet.setHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return okHttpPost(url, content, "application/json; charset=utf-8", null);
    }

    /**
     * http post json 请求方法
     *
     * @param url 请求地址
     * @return string json
     */
    public static String okHttpPostJson(String url, String content) {
        return okHttpPost(url, content, "application/json; charset=utf-8", null);
    }

    public static ResponseBody okHttpPostJsonResponseBody(String url,
                                                          String content) {
        return okHttpPostResponseBody(url, content,
                "application/json; charset=utf-8", null);
    }

    /**
     * http post 请求方法
     *
     * @param url 请求地址
     * @return string
     */
    public static String okHttpPost(String url, String content,
                                    String mediaType, Headers headers) {
        if (url == null || url.length() <= 0) {
            return "";
        }
        String result = null;
        try {
            Request request = null;
            // 是否有请求头信息
            if (headers == null) {
                request = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(MediaType.parse(mediaType),
                                content)).build();
            } else {
                request = new Request.Builder()
                        .url(url)
                        .headers(headers)
                        .post(RequestBody.create(MediaType.parse(mediaType),
                                content)).build();
            }
            log.debug("request  : " + request + " body: " + content);
            Response response = httpClinet.newCall(request).execute();
            log.debug("response :" + response);
            if (!response.isSuccessful()) {
                return "";
            }
            result = response.body().string();
            log.debug("rev result :" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * http post 请求方法
     *
     * @param url 请求地址
     * @return string
     */
    public static ResponseBody okHttpPostResponseBody(String url,
                                                      String content, String mediaType, Headers headers) {
        if (url == null || url.length() <= 0) {
            return null;
        }
        ResponseBody result = null;
        try {
            Request request = null;
            // 是否有请求头信息
            if (headers == null) {
                request = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(MediaType.parse(mediaType),
                                content)).build();
            } else {
                request = new Request.Builder()
                        .url(url)
                        .headers(headers)
                        .post(RequestBody.create(MediaType.parse(mediaType),
                                content)).build();
            }
            log.debug("request  : " + request + " body: " + content);
            Response response = httpClinet.newCall(request).execute();
            log.debug("response :" + response);
            if (!response.isSuccessful()) {
                return null;
            }
            result = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * http get 请求方法
     *
     * @param url 请求地址
     * @return string
     */
    public static String okHttpGet(String url) {
        return okHttpGet(url, null);
    }

    /**
     * http get 请求方法
     *
     * @param url 请求地址
     *            请求头
     * @return string
     */
    public static JSONObject okHttpGetJson(String url) {
        if (url == null || url.length() <= 0) {
            return null;
        }
        JSONObject result = null;
        try {
            Request request = null;
            // 是否有请求头信息
            request = new Request.Builder().addHeader("Connection", "close")
                    .url(url).get().build();
            log.debug("request : " + request);
            Response response = httpClinet.newCall(request).execute();
            log.debug("response :" + response);
            if (!response.isSuccessful()) {
                return null;
            }
            result = JSON.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * http get 请求方法
     *
     * @param url     请求地址
     * @param headers 请求头
     * @return string
     */
    public static String okHttpGet(String url, Headers headers) {
        if (url == null || url.length() <= 0) {
            return "";
        }
        String result = null;
        try {
            Request request = null;
            // 是否有请求头信息
            if (headers == null) {
                request = new Request.Builder().url(url).get().build();
            } else {
                request = new Request.Builder().url(url).headers(headers).get()
                        .build();
            }
            log.debug("request  : " + request);
            Response response = httpClinet.newCall(request).execute();
            log.debug("response :" + response);
            if (!response.isSuccessful()) {
                return "";
            }
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static SSLSocketFactory getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
            return sslSocketFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
