package vip.gadfly.tiktok.open.cache;

public interface IAccessTokenCache {
    Object get(String key);

    String getStr(String key);

    IAccessTokenCache set(String key, String value);

    IAccessTokenCache set(String key, String value, Long time);

    IAccessTokenCache remove(String key);

    boolean isContainKey(String key);

}
