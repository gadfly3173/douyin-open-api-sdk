package vip.gadfly.tiktok.open.api;

import vip.gadfly.tiktok.open.bean.userinfo.TtOpUserInfoResult;

/**
 * @author Gadfly
 * @since 2021-09-30 10:27
 */
public interface TtOpUserInfoService {
    /**
     * 创建抖音视频
     *
     * @param openId  用户openid
     * @return 结果
     */
    TtOpUserInfoResult getUserInfo(String openId);
}
