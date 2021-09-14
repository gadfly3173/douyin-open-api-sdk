package vip.gadfly.tiktok.open.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信错误码
 *
 * @author OF
 */
public class ApiErrorCode {

    /**
     * 全局返回码说明
     */
    private final static Map<Integer, String> ERROR_CODE = new HashMap<Integer, String>();

    static {
        ERROR_CODE.put(-1, "系统繁忙，此时请开发者稍候再试");
        ERROR_CODE.put(0, "请求成功");
        ERROR_CODE.put(10008,
                "获取access_token时AppSecret错误，或者access_token无效");
        ERROR_CODE.put(2190008,
                "获取access_token时AppSecret错误，或者access_token无效");
        ERROR_CODE.put(10010,
                "获取access_token时AppSecret错误，或者access_token无效");

    }

    /**
     * 异常代码识别
     *
     * @param statusCode 异常代码
     * @return 错误信息
     */
    protected static String getErrorCode(int statusCode) {
        if (ERROR_CODE.containsKey(statusCode)) {
            // 根据错误码返回错误代码
            return statusCode + ":" + ERROR_CODE.get(statusCode);
        }
        return statusCode + ":操作异常";
    }

    /**
     * 通过返回码获取返回信息
     *
     * @param errCode 错误码
     * @return {String}
     */
    public static String getErrCode(String errCode) {
        String result = ERROR_CODE.get(errCode);
        return result != null ? result : "未知返回码：" + errCode;
    }
}
