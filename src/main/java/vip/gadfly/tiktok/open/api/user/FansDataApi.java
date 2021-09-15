package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class FansDataApi extends ApiBase {
    public String FANS_LIST_URL = getHttpUrl() + "/fans/data";

    /**
     * 获取用户粉丝统计数据
     *
     * @param param FansDataParam
     * @return
     */
    public FansDataResult get(FansDataParam param) {
        String url = FANS_LIST_URL + "?" + param.getNoPageUrlParam();
        FansDataResult result = sendGet(url).dataToBean(FansDataResult.class);
        return result;
    }

    /**
     * 获取用户粉丝统计数据
     *
     * @return
     */
    public FansDataResult get() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        FansDataParam param = new FansDataParam();
        param.setAccessToken(accessToken);
        param.setOpenId(getOpenId());
        //请求
        return get(param);
    }


    public FansDataApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public FansDataApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    /**
     * scope
     *
     * @return
     */
    public String scope() {
        return DouyinConf.SCOPE_FANS_DATA;
    }
}
