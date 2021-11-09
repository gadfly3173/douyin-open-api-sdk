package vip.gadfly.tiktok.open.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.open.api.TtOpUserInfoService;
import vip.gadfly.tiktok.open.bean.userinfo.TtOpFansCheckResult;
import vip.gadfly.tiktok.open.bean.userinfo.TtOpFansListResult;
import vip.gadfly.tiktok.open.bean.userinfo.TtOpFollowingListResult;
import vip.gadfly.tiktok.open.bean.userinfo.TtOpUserInfoResult;
import vip.gadfly.tiktok.open.common.ITtOpBaseService;

import static vip.gadfly.tiktok.core.enums.TtOpApiUrl.UserInfo.*;

/**
 * @author Gadfly
 * @since 2021-09-30 14:42
 */
@Slf4j
@RequiredArgsConstructor
public class TtOpUserInfoServiceImpl implements TtOpUserInfoService {
    private final ITtOpBaseService ttOpBaseService;

    protected TtOpConfigStorage getTtOpConfigStorage() {
        return this.ttOpBaseService.getTtOpConfigStorage();
    }

    @Override
    public TtOpUserInfoResult getUserInfo(String openId) {
        log.debug("获取用户信息，收到的参数：openid={}", openId);
        String url = String.format(
                GET_USER_INFO_URL.getUrl(getTtOpConfigStorage()),
                openId,
                this.ttOpBaseService.getAccessToken(openId));
        log.debug("url={}", url);
        return this.ttOpBaseService.get(url, TtOpUserInfoResult.class);
    }

    @Override
    public TtOpFansListResult getFansList(String openId, Long cursor, Integer count) {
        log.debug("获取粉丝列表，收到的参数：openid={}, cursor={}, count={}", openId, cursor, count);
        String url = String.format(
                GET_FANS_LIST_URL.getUrl(getTtOpConfigStorage()),
                openId,
                cursor,
                count,
                this.ttOpBaseService.getAccessToken(openId));
        log.debug("url={}", url);
        return this.ttOpBaseService.get(url, TtOpFansListResult.class);
    }

    @Override
    public TtOpFollowingListResult getFollowingList(String openId, Long cursor, Integer count) {
        log.debug("获取关注列表，收到的参数：openid={}, cursor={}, count={}", openId, cursor, count);
        String url = String.format(
                GET_FOLLOWING_LIST_URL.getUrl(getTtOpConfigStorage()),
                openId,
                cursor,
                count,
                this.ttOpBaseService.getAccessToken(openId));
        log.debug("url={}", url);
        return this.ttOpBaseService.get(url, TtOpFollowingListResult.class);
    }

    @Override
    public TtOpFansCheckResult getFansCheck(String openId, String followerOpenId) {
        log.debug("粉丝判断，收到的参数：openid={}, followerOpenId={}", openId, followerOpenId);
        String url = String.format(
                GET_FANS_CHECK_URL.getUrl(getTtOpConfigStorage()),
                openId,
                followerOpenId,
                this.ttOpBaseService.getAccessToken(openId));
        log.debug("url={}", url);
        return this.ttOpBaseService.get(url, TtOpFansCheckResult.class);
    }
}
