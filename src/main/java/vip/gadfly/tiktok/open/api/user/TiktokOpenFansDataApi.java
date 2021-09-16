package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class TiktokOpenFansDataApi extends AbstractTiktokOpenApiBase {
    public String FANS_LIST_URL = getHttpUrl() + "/fans/data";

    /**
     * 获取用户粉丝统计数据
     *
     * @param param TiktokOpenFansDataParam
     * @return
     */
    public TiktokOpenFansDataResult get(TiktokOpenFansDataParam param) {
        String url = FANS_LIST_URL + "?" + param.getNoPageUrlParam();
        TiktokOpenFansDataResult result = sendGet(url).dataToBean(TiktokOpenFansDataResult.class);
        return result;
    }

    /**
     * 获取用户粉丝统计数据
     *
     * @return
     */
    public TiktokOpenFansDataResult get() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TiktokOpenFansDataParam param = new TiktokOpenFansDataParam();
        param.setAccessToken(accessToken);
        param.setOpenId(getOpenId());
        //请求
        return get(param);
    }


    public TiktokOpenFansDataApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TiktokOpenFansDataApi withOpenId(String openId) {
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
