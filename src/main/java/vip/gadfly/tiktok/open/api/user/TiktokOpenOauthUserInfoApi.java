package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.TiktokOpenApiResponse;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class TiktokOpenOauthUserInfoApi extends AbstractTiktokOpenApiBase {
    public String OAUTH_USERINFO_URL = getHttpUrl() + "/oauth/userinfo";

    /**
     * 获取用户的抖音公开信息，包含昵称、头像、性别和地区
     *
     * @param param TiktokOpenOauthUserInfoParam
     * @return
     */
    public TiktokOpenOauthUserInfoResult get(TiktokOpenOauthUserInfoParam param) {
        String url = OAUTH_USERINFO_URL + "?" + param.getUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenOauthUserInfoResult result = response.dataToBean(TiktokOpenOauthUserInfoResult.class);
        return result;
    }

    /**
     * 获取用户的抖音公开信息，包含昵称、头像、性别和地区
     *
     * @return
     */
    public TiktokOpenOauthUserInfoResult get() {
        //从缓存中获取 token
        String accessToken = getAccessToken();
        //参数拼装
        TiktokOpenOauthUserInfoParam param = new TiktokOpenOauthUserInfoParam();
        param.setOpenId(getOpenId())
                .setAccessToken(accessToken);
        return get(param);
    }

    public TiktokOpenOauthUserInfoApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TiktokOpenOauthUserInfoApi withOpenId(String openId) {
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
