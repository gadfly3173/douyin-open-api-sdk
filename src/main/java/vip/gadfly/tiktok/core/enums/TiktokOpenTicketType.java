package vip.gadfly.tiktok.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ticket类型枚举
 *
 * @author Gadfly
 */
@Getter
@RequiredArgsConstructor
public enum TiktokOpenTicketType {
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
