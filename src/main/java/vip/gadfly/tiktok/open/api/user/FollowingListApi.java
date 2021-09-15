package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class FollowingListApi extends ApiBase {
    public String FOLLOWING_LIST_URL = getHttpUrl() + "/following/list";

    /**
     * 关注列表
     *
     * @param param FollowingListParam
     * @return
     */
    public FollowingListResult page(FollowingListParam param) {
        String url = FOLLOWING_LIST_URL + "?" + param.getUrlParam();
        FollowingListResult result = sendGet(url).dataToBean(FollowingListResult.class);
        return result;
    }


    /**
     * 查出样例(默认为一页50条)
     *
     * @return
     */
    public FollowingListResult take() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        FollowingListParam param = new FollowingListParam();
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
    public FollowingListResult page(Long cursor, Long count) {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        FollowingListParam param = new FollowingListParam();
        param.setAccessToken(accessToken)
                .setOpenId(getOpenId())
                .setCursor(cursor)
                .setCount(count);
        return page(param);
    }

    public FollowingListApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public FollowingListApi withOpenId(String openId) {
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
