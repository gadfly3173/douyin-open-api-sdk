package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

public class TtOpFollowingListApi extends AbstractTtOpApiBase {
    public String FOLLOWING_LIST_URL = getHttpUrl() + "/following/list";

    /**
     * 关注列表
     *
     * @param param TtOpFollowingListParam
     * @return
     */
    public TtOpFollowingListResult page(TtOpFollowingListParam param) {
        String url = FOLLOWING_LIST_URL + "?" + param.getUrlParam();
        TtOpFollowingListResult result = sendGet(url).dataToBean(TtOpFollowingListResult.class);
        return result;
    }


    /**
     * 查出样例(默认为一页50条)
     *
     * @return
     */
    public TtOpFollowingListResult take() {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TtOpFollowingListParam param = new TtOpFollowingListParam();
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
    public TtOpFollowingListResult page(Long cursor, Long count) {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TtOpFollowingListParam param = new TtOpFollowingListParam();
        param.setAccessToken(accessToken)
                .setOpenId(getOpenId())
                .setCursor(cursor)
                .setCount(count);
        return page(param);
    }

    public TtOpFollowingListApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TtOpFollowingListApi withOpenId(String openId) {
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
