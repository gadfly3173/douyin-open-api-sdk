package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.common.TtOpApiResponse;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class TtOpOauthUserInfoApi extends AbstractTtOpApiBase {
    public String OAUTH_USERINFO_URL = getHttpUrl() + "/oauth/userinfo";

    /**
     * 获取用户的抖音公开信息，包含昵称、头像、性别和地区
     *
     * @param param TtOpOauthUserInfoParam
     * @return
     */
    public TtOpOauthUserInfoResult get(TtOpOauthUserInfoParam param) {
        String url = OAUTH_USERINFO_URL + "?" + param.getUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpOauthUserInfoResult result = response.dataToBean(TtOpOauthUserInfoResult.class);
        return result;
    }

    /**
     * 获取用户的抖音公开信息，包含昵称、头像、性别和地区
     *
     * @return
     */
    public TtOpOauthUserInfoResult get() {
        //从缓存中获取 token
        String accessToken = getAccessToken();
        //参数拼装
        TtOpOauthUserInfoParam param = new TtOpOauthUserInfoParam();
        param.setOpenId(getOpenId())
                .setAccessToken(accessToken);
        return get(param);
    }

    public TtOpOauthUserInfoApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TtOpOauthUserInfoApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    /**
     * scope
     *
     * @return
     */
    public String scope() {
        return DouyinConf.SCOPE_USER_INFO;
    }
}
