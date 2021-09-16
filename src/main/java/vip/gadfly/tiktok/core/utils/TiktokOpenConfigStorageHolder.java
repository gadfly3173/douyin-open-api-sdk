package vip.gadfly.tiktok.core.utils;

/**
 * @author yd
 * @date 2019-03-20 22:06
 */
public class TiktokOpenConfigStorageHolder {
    private final static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "default";
        }
    };

    public static String get() {
        return THREAD_LOCAL.get();
    }

    public static void set(String label) {
        THREAD_LOCAL.set(label);
    }

    /**
     * 此方法需要用户根据自己程序代码，在适当位置手动触发调用，本SDK里无法判断调用时机
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
