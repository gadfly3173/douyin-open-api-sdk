package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class TtOpFansDataApi extends AbstractTtOpApiBase {
    public String FANS_LIST_URL = getHttpUrl() + "/fans/data";

    /**
     * 获取用户粉丝统计数据
     *
     * @param param TtOpFansDataParam
     * @return
     */
    public TtOpFansDataResult get(TtOpFansDataParam param) {
        String url = FANS_LIST_URL + "?" + param.getNoPageUrlParam();
        TtOpFansDataResult result = sendGet(url).dataToBean(TtOpFansDataResult.class);
        return result;
    }

    /**
     * 获取用户粉丝统计数据
     *
     * @return
     */
    public TtOpFansDataResult get() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TtOpFansDataParam param = new TtOpFansDataParam();
        param.setAccessToken(accessToken);
        param.setOpenId(getOpenId());
        //请求
        return get(param);
    }


    public TtOpFansDataApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TtOpFansDataApi withOpenId(String openId) {
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
