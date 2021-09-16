package vip.gadfly.tiktok.open.cache;

public interface ITiktokOpenAccessTokenCache {
    Object get(String key);

    String getStr(String key);

    ITiktokOpenAccessTokenCache set(String key, String value);

    ITiktokOpenAccessTokenCache set(String key, String value, Long time);

    ITiktokOpenAccessTokenCache remove(String key);

    boolean isContainKey(String key);

}
