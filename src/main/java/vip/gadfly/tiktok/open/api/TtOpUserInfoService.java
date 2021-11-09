package vip.gadfly.tiktok.open.api;

import vip.gadfly.tiktok.open.bean.userinfo.TtOpFansCheckResult;
import vip.gadfly.tiktok.open.bean.userinfo.TtOpFansListResult;
import vip.gadfly.tiktok.open.bean.userinfo.TtOpFollowingListResult;
import vip.gadfly.tiktok.open.bean.userinfo.TtOpUserInfoResult;

/**
 * @author Gadfly
 * @since 2021-09-30 10:27
 */
public interface TtOpUserInfoService {
    /**
     * 创建抖音视频
     *
     * @param openId 用户openid
     * @return 结果
     */
    TtOpUserInfoResult getUserInfo(String openId);

    /**
     * 获取粉丝列表
     *
     * @param openId openid
     * @param cursor 游标
     * @param count  数量
     * @return 结果
     */
    TtOpFansListResult getFansList(String openId, Long cursor, Integer count);

    /**
     * 获取关注列表
     *
     * @param openId openid
     * @param cursor 游标
     * @param count  数量
     * @return 结果
     */
    TtOpFollowingListResult getFollowingList(String openId, Long cursor, Integer count);

    /**
     * 粉丝判断
     *
     * @param openId         openid
     * @param followerOpenId 粉丝openid
     * @return 结果
     */
    TtOpFansCheckResult getFansCheck(String openId, String followerOpenId);
}
