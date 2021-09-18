package vip.gadfly.tiktok.core.exception;

/**
 * @author yangyidian
 * @date 2021/06/19
 **/
public interface ITtOpError {

    /**
     * 获取错误编码
     *
     * @return
     */
    Integer getErrorCode();

    /**
     * 获取错误信息
     *
     * @return
     */
    String getDescription();

    Boolean checkSuccess();
}
