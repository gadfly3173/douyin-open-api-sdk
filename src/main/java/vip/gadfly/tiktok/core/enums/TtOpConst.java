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
        public static final String ITEM_DIGG = "item_digg";
        public static final String USER_FOLLOW = "user_follow";
        public static final String ENTERPRISE_COMMENT_EVENT = "enterprise_comment_event";
    }

    /**
     * webhook收到私信消息的message type
     */
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

    /**
     * 用户点赞事件类型
     */
    public static class WebhookDiggActionType {
        /**
         * 文本消息&未知消息
         */
        public static final int ACTION_FOLLOW = 1;
    }

    /**
     * 用户关注事件类型
     */
    public static class WebhookFollowActionType {
        /**
         * 文本消息&未知消息
         */
        public static final int ACTION_FOLLOW = 1;
        /**
         * 取关操作
         */
        public static final int ACTION_UNFOLLOW = 2;
        /**
         * 设置管理员操作
         */
        public static final int ACTION_SET_ADMIN = 3;
        /**
         * 取消设置管理员操作
         */
        public static final int ACTION_UNSET_ADMIN = 4;
        /**
         * 拉黑操作
         */
        public static final int ACTION_BLOCK = 5;
        /**
         * 取消拉黑操作
         */
        public static final int ACTION_UNBLOCK = 6;
        /**
         * 添加互关好友操作
         */
        public static final int ACTION_FRIEND = 101;
        /**
         * 取消互关好友操作
         */
        public static final int ACTION_UNFRIEND = 102;
    }
}
