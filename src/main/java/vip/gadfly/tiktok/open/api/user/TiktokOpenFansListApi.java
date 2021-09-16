package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class TiktokOpenFansListApi extends AbstractTiktokOpenApiBase {
    public String FANS_LIST_URL = getHttpUrl() + "/fans/list";

    /**
     * 粉丝列表
     *
     * @param param TiktokOpenFansListParam
     * @return
     */
    public TiktokOpenFansListResult page(TiktokOpenFansListParam param) {

        if (param != null && param.getAccessToken() == null) {
            //取缓存token
            String accessToken = getAccessToken();
            param.setAccessToken(accessToken);
        }

        String url = FANS_LIST_URL + "?" + param.getUrlParam();
        TiktokOpenFansListResult result = sendGet(url).dataToBean(TiktokOpenFansListResult.class);
        return result;
    }


    /**
     * 根据 appId/openId 查出样例(默认为一页50条)
     *
     * @return
     */
    public TiktokOpenFansListResult take() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TiktokOpenFansListParam param = new TiktokOpenFansListParam();
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
    public TiktokOpenFansListResult page(Long cursor, Long count) {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TiktokOpenFansListParam param = new TiktokOpenFansListParam();
        param.setAccessToken(accessToken)
                .setOpenId(getOpenId())
                .setCursor(cursor)
                .setCount(count);
        return page(param);
    }

    public TiktokOpenFansListApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TiktokOpenFansListApi withOpenId(String openId) {
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
