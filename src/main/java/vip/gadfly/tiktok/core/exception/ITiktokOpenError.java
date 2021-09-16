package vip.gadfly.tiktok.core.exception;

/**
 * @author yangyidian
 * @date 2021/06/19
 **/
public interface ITiktokOpenError {

    /**
     * 获取错误编码
     * @return
     */
    Integer errorCode();

    /**
     * 获取错误信息
     * @return
     */
    String errorMessage();

    Boolean checkSuccess();
}
