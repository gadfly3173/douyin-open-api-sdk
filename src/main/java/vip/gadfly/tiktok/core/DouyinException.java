package vip.gadfly.tiktok.core;

import java.util.Map;

public class DouyinException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorCode = null;
    private Map<String, Object> returnData;

    /**
     * @param string
     */
    public DouyinException(String string) {
        super(string);
    }

    public DouyinException(String message, Map<String, Object> returnData) {
        super(message);
        this.returnData = returnData;
    }

    public DouyinException(String errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getReturnData() {
        return returnData;
    }

}
