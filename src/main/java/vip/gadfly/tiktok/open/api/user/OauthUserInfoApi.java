package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.base.ApiResponse;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class OauthUserInfoApi extends ApiBase {
    public static String OAUTH_USERINFO_URL = AppConfig.getInstance().httpUrl + "/oauth/userinfo";

    /**
     * 获取用户的抖音公开信息，包含昵称、头像、性别和地区
     *
     * @param param OauthUserInfoParam
     * @return
     */
    public static OauthUserInfoResult get(OauthUserInfoParam param) {
        String url = OAUTH_USERINFO_URL + "?" + param.getUrlParam();
        ApiResponse response = sendGet(url);
        OauthUserInfoResult result = response.dataToBean(OauthUserInfoResult.class);
        return result;
    }

    /**
     * 获取用户的抖音公开信息，包含昵称、头像、性别和地区
     *
     * @return
     */
    public OauthUserInfoResult get() {
        //从缓存中获取 token
        String accessToken = getAccessToken();
        //参数拼装
        OauthUserInfoParam param = new OauthUserInfoParam();
        param.setOpenId(getOpenId())
                .setAccessToken(accessToken);
        return get(param);
    }

    public OauthUserInfoApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public OauthUserInfoApi withOpenId(String openId) {
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
