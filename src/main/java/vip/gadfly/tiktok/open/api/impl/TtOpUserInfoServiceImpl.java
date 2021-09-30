package vip.gadfly.tiktok.open.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.open.api.TtOpUserInfoService;
import vip.gadfly.tiktok.open.bean.userinfo.TtOpUserInfoResult;
import vip.gadfly.tiktok.open.common.ITtOpBaseService;

import static vip.gadfly.tiktok.core.enums.TtOpApiUrl.UserInfo.GET_USER_INFO_URL;

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
}
