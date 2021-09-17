package vip.gadfly.tiktok.core.exception;

import lombok.Getter;

/**
 * 错误码
 * https://open.douyin.com/platform/doc/6850392252464809992
 *
 * @author yangyidian
 * @author Gadfly
 * @since 2020/06/28
 **/
@Getter
public enum TiktokOpenErrorMsgEnum {
    CODE_0(0, "成功"),
    CODE_10002(10002, "参数错误"),
    CODE_10003(10003, "client_key 错误"),
    CODE_10004(10004, "应用权限不足，请申请对应的scope权限"),
    CODE_10005(10005, "缺少参数"),
    CODE_10006(10006, "非法重定向URI, 需要与APP配置中的\"授权回调域\"一致。"),
    CODE_10007(10007, "授权码过期"),
    CODE_10008(10008, "access_token 无效或过期"),
    CODE_10010(10010, "refresh_token 无效或过期"),
    CODE_10011(10011, "应用包名与配置不一致"),
    CODE_10012(10012, "应用正在审核中，无法进行授权"),
    CODE_10013(10013, "clientkey 或者clientsecret 错误"),
    CODE_10014(10014, "授权的clientkey与获取accesstoken时传递的client_key不一致"),
    CODE_10015(10015, "应用类型错误，如将APP应用的client_key 用于 PC 应用"),
    CODE_10017(10017, "安卓应用签名与配置不一致，请检查签名信息"),
    CODE_10020(10020, "更新新refresh_token次数超出限制"),
    CODE_2100004(2100004, "系统繁忙，此时请开发者稍候再试"),
    CODE_2100005(2100005, "参数不合法"),
    CODE_2100007(2100007, "无权限操作"),
    CODE_2100009(2100009, "用户被禁封使用该操作"),
    CODE_2190001(2190001, "quota已用完"),
    CODE_2190002(2190002, "access_token无效"),
    CODE_2190003(2190003, "用户未授权该api"),
    CODE_2190004(2190004, "应用未获得该能力, 请去https://open.douyin.com/申请"),
    CODE_2190005(2190005, "视频文件太大了"),
    CODE_2190006(2190006, "视频时长不能超过15分钟"),
    CODE_2190007(2190007, "无效的视频文件id"),
    CODE_2190008(2190008, "access_token过期,请刷新或重新授权"),
    CODE_2190015(2190015, "请求参数access_token openid不匹配"),
    CODE_2190016(2190016, "当前应用已被封禁或下线"),
    CODE_2114005(2114005, "视频投稿功能已封禁，详情见抖音端上【消息-系统通知】"),
    CODE_2114007(2114007, "视频发布数量超过每日上限"),
    CODE_2111001(2111001, "命中敏感词"),
    CODE_2111002(2111002, "获取评论失败"),
    CODE_2111003(2111003, "无效的评论"),
    CODE_2111004(2111004, "非本视频评论"),
    CODE_2111005(2111005, "上一个置顶评论正在审核中, 请稍后再试"),
    CODE_2111006(2111006, "评论失败"),
    CODE_2111007(2111007, "获取视频失败"),
    CODE_2112001(2112001, "非企业号用户"),
    CODE_2113001(2113001, "素材不符合要求, 未通过审核"),
    CODE_2113002(2113002, "您的素材数量已达上限"),
    ;

    private final int code;
    private final String msg;

    TiktokOpenErrorMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过错误代码查找其中文含义.
     */
    public static String findMsgByCode(int code) {
        for (TiktokOpenErrorMsgEnum value : TiktokOpenErrorMsgEnum.values()) {
            if (value.code == code) {
                return value.msg;
            }
        }
        return "";
    }
}
