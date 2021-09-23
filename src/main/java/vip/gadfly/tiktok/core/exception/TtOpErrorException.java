package vip.gadfly.tiktok.core.exception;

import vip.gadfly.tiktok.open.common.TtOpBaseResult;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
public class TtOpErrorException extends RuntimeException {

    private final ITtOpError error;

    public TtOpErrorException(TtOpBaseResult error) {
        super(error.getDescription());
        TtOpError newErr = new TtOpError();
        newErr.setErrorCode(error.getErrorCode());
        newErr.setDescription(error.getDescription());
        this.error = newErr;
    }

    public TtOpErrorException(ITtOpError error) {
        super(error.getDescription());
        this.error = error;
    }

    public TtOpErrorException(ITtOpError error, Throwable cause) {
        super(error.getDescription(), cause);
        this.error = error;
    }

    public ITtOpError getError() {
        return this.error;
    }
}
