package vip.gadfly.tiktok.core.exception;

/**
 * @author yangyidian
 * @date 2020/06/28
 **/
public class TiktokOpenErrorException extends RuntimeException {

    private final ITiktokOpenError error;

    public TiktokOpenErrorException(ITiktokOpenError error) {
        super(error.toString());
        this.error = error;
    }

    public TiktokOpenErrorException(ITiktokOpenError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public ITiktokOpenError getError() {
        return this.error;
    }
}
