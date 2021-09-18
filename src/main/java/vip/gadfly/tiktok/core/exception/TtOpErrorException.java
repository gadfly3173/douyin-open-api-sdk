package vip.gadfly.tiktok.core.exception;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
public class TtOpErrorException extends RuntimeException {

    private final ITtOpError error;

    public TtOpErrorException(ITtOpError error) {
        super(error.toString());
        this.error = error;
    }

    public TtOpErrorException(ITtOpError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public ITtOpError getError() {
        return this.error;
    }
}
