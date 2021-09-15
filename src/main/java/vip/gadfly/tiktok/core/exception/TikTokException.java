package vip.gadfly.tiktok.core.exception;

import java.util.Map;

public class TikTokException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer errorCode = 0;
    private Map<String, Object> returnData;

    /**
     * @param string
     */
    public TikTokException(String string) {
        super(string);
    }

    public TikTokException(String message, Map<String, Object> returnData) {
        super(message);
        this.returnData = returnData;
    }

    public TikTokException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TikTokException(Throwable e) {
        super(e);
    }

    public TikTokException(String msg, Throwable e) {
        super(msg, e);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getReturnData() {
        return returnData;
    }

}
