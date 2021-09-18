package vip.gadfly.tiktok.open.cache;

public interface ITtOpAccessTokenCache {
    Object get(String key);

    String getStr(String key);

    ITtOpAccessTokenCache set(String key, String value);

    ITtOpAccessTokenCache set(String key, String value, Integer time);

    ITtOpAccessTokenCache remove(String key);

    boolean isContainKey(String key);

}
