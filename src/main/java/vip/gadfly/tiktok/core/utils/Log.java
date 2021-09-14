package vip.gadfly.tiktok.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共日志类
 *
 * @author OF
 * @date 2016年8月18日
 */
public class Log {
    private static final Logger log = LoggerFactory.getLogger("douyin_log");

    public static void info(String string) {
        log.info(string);
    }

    public static void info(String string, Object obj) {
        log.info(string, obj);
    }

    public static void info(String string, Object... obj) {
        log.info(string, obj);
    }

    public static void info(String string, Object obj, Object obj1) {
        log.info(string, obj, obj1);
    }

    public static void info(String string, Throwable throwable) {
        log.info(string, throwable);
    }

    public static void error(String string, Throwable throwable) {
        log.error(string, throwable);
    }

    public static void error(String string) {
        log.error(string);
    }

    public static void error(String string, Object obj) {
        log.error(string, obj);
    }

    public static void error(String string, Object... obj) {
        log.error(string, obj);
    }

    public static void error(String string, Object obj, Object obj1) {
        log.error(string, obj, obj1);
    }

    public static void debug(String string, Throwable throwable) {
        log.debug(string, throwable);
    }

    public static void debug(String string) {
        log.debug(string);
    }

    public static void debug(String string, Object obj) {
        log.debug(string, obj);
    }

    public static void debug(String string, Object... obj) {
        log.debug(string, obj);
    }

    public static void debug(String string, Object obj, Object obj1) {
        log.debug(string, obj, obj1);
    }

    public static void main(String[] args) {
        Log.info("123:{{}}", "1231232313");
        Log.log.trace("trace");
        Log.debug("debug str");
        Log.info("info str");
        Log.log.warn("warn");
        Log.error("error");
    }
}
