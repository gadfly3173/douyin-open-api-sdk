package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class TtOpFansListApi extends AbstractTtOpApiBase {
    public String FANS_LIST_URL = getHttpUrl() + "/fans/list";

    /**
     * 粉丝列表
     *
     * @param param TtOpFansListParam
     * @return
     */
    public TtOpFansListResult page(TtOpFansListParam param) {

        if (param != null && param.getAccessToken() == null) {
            //取缓存token
            String accessToken = getAccessToken();
            param.setAccessToken(accessToken);
        }

        String url = FANS_LIST_URL + "?" + param.getUrlParam();
        TtOpFansListResult result = sendGet(url).dataToBean(TtOpFansListResult.class);
        return result;
    }


    /**
     * 根据 appId/openId 查出样例(默认为一页50条)
     *
     * @return
     */
    public TtOpFansListResult take() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TtOpFansListParam param = new TtOpFansListParam();
        param.setAccessToken(accessToken)
                .setOpenId(getOpenId());
        return page(param);
    }

    /**
     * 分页获取 fans
     *
     * @param cursor 页码
     * @param count  页数
     * @return
     */
    public TtOpFansListResult page(Long cursor, Long count) {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TtOpFansListParam param = new TtOpFansListParam();
        param.setAccessToken(accessToken)
                .setOpenId(getOpenId())
                .setCursor(cursor)
                .setCount(count);
        return page(param);
    }

    public TtOpFansListApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TtOpFansListApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    /**
     * scope
     *
     * @return
     */
    public String scope() {

        return DouyinConf.SCOPE_FANS_LIST;
    }


}
