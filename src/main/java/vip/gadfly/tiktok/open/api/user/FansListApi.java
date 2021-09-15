package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class FansListApi extends ApiBase {
    public String FANS_LIST_URL = getHttpUrl() + "/fans/list";

    /**
     * 粉丝列表
     *
     * @param param FansListParam
     * @return
     */
    public FansListResult page(FansListParam param) {

        if (param != null && param.getAccessToken() == null) {
            //取缓存token
            String accessToken = getAccessToken();
            param.setAccessToken(accessToken);
        }

        String url = FANS_LIST_URL + "?" + param.getUrlParam();
        FansListResult result = sendGet(url).dataToBean(FansListResult.class);
        return result;
    }


    /**
     * 根据 appId/openId 查出样例(默认为一页50条)
     *
     * @return
     */
    public FansListResult take() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        FansListParam param = new FansListParam();
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
    public FansListResult page(Long cursor, Long count) {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        FansListParam param = new FansListParam();
        param.setAccessToken(accessToken)
                .setOpenId(getOpenId())
                .setCursor(cursor)
                .setCount(count);
        return page(param);
    }

    public FansListApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public FansListApi withOpenId(String openId) {
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
