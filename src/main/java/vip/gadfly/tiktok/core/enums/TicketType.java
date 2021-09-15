package vip.gadfly.tiktok.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * ticket类型枚举
 * Created by Binary Wang on 2018/11/18.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Getter
@RequiredArgsConstructor
public enum TicketType {
    /**
     * client
     */
    CLIENT("client_token"),
    /**
     * jsapi
     */
    JSAPI("ticket");

    /**
     * type代码
     */
    private final String code;

}
