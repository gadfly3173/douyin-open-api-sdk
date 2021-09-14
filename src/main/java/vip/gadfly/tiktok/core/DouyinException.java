package vip.gadfly.tiktok.core;

import java.util.Map;

public class DouyinException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String erroCode = null;
    private Map<String, Object> returndata;

    /**
     * @param string
     */
    public DouyinException(String string) {
        super(string);
    }

    public DouyinException(String message, Map<String, Object> returndata) {
        super(message);
        this.returndata = returndata;
    }

    public DouyinException(String erroCode, String msg) {
        super(msg);
        this.erroCode = erroCode;
    }

    public String getErroCode() {
        return erroCode;
    }

    public Map<String, Object> getReturndata() {
        return returndata;
    }

}
