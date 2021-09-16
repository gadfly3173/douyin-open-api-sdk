package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.base.TiktokOpenApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class TiktokOpenFollowingListApi extends TiktokOpenApiBase {
    public String FOLLOWING_LIST_URL = getHttpUrl() + "/following/list";

    /**
     * 关注列表
     *
     * @param param TiktokOpenFollowingListParam
     * @return
     */
    public TiktokOpenFollowingListResult page(TiktokOpenFollowingListParam param) {
        String url = FOLLOWING_LIST_URL + "?" + param.getUrlParam();
        TiktokOpenFollowingListResult result = sendGet(url).dataToBean(TiktokOpenFollowingListResult.class);
        return result;
    }


    /**
     * 查出样例(默认为一页50条)
     *
     * @return
     */
    public TiktokOpenFollowingListResult take() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TiktokOpenFollowingListParam param = new TiktokOpenFollowingListParam();
        param.setOpenId(getOpenId())
                .setAccessToken(accessToken);
        return page(param);
    }

    /**
     * 分页获取 fans
     *
     * @param cursor 页码
     * @param count  页数
     * @return
     */
    public TiktokOpenFollowingListResult page(Long cursor, Long count) {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TiktokOpenFollowingListParam param = new TiktokOpenFollowingListParam();
        param.setAccessToken(accessToken)
                .setOpenId(getOpenId())
                .setCursor(cursor)
                .setCount(count);
        return page(param);
    }

    public TiktokOpenFollowingListApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TiktokOpenFollowingListApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    /**
     * scope
     *
     * @return
     */
    public String scope() {

        return DouyinConf.SCOPE_FOLLOWING_LIST;
    }


}
