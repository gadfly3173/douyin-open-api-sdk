package vip.gadfly.tiktok.core.enums;

/**
 * @author Gadfly
 * @since 2021-09-22 15:06
 */
public class TtOpConst {
    public static class WebhookEventType {
        public static final String VERIFY_WEBHOOK = "verify_webhook";
        public static final String CREATE_VIDEO = "create_video";
        public static final String AUTHORIZE = "authorize";
        public static final String UNAUTHORIZE = "unauthorize";
        public static final String RECEIVE_MSG = "receive_msg";
        public static final String ENTER_IM = "enter_im";
        public static final String DIAL_PHONE = "dial_phone";
        public static final String WEBSITE_CONTACT = "website_contact";
        public static final String PERSONALTABCONTACT = "personaltabcontact";
    }

    public static class WebhookMsgType {
        /**
         * 文本消息&未知消息
         */
        public static final String TEXT = "text";
        /**
         * 表情消息
         */
        public static final String EMOJI = "emoji";
        /**
         * 卡片消息
         */
        public static final String H5_POI = "h5/poi";
        /**
         * 企业号消息卡片消息
         */
        public static final String CARD = "card";
    }
}
