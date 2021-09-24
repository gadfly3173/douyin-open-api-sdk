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
public enum TtOpTicketType {
    /**
     * client
     */
    CLIENT("client_token"),
    /**
     * jsapi
     */
    JSAPI("jsapi_ticket");

    /**
     * type代码
     */
    private final String code;

}
