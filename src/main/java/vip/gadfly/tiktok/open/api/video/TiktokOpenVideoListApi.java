package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.base.TiktokOpenApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * Scope: video.list
 */
public class TiktokOpenVideoListApi extends TiktokOpenApiBase {
    public String VIDEO_LIST = getHttpUrl() + "/video/list/";

    /**
     * 列出已发布的视频
     *
     * @param param TiktokOpenVideoListParam
     * @return
     */
    public TiktokOpenVideoListResult page(TiktokOpenVideoListParam param) {
        String url = VIDEO_LIST + "?" + param.getUrlParam();
        TiktokOpenVideoListResult result = sendGet(url).dataToBean(TiktokOpenVideoListResult.class);
        return result;
    }

    /**
     * 列出已发布的视频
     *
     * @return
     */
    public TiktokOpenVideoListResult take(Long count) {
        TiktokOpenVideoListParam param = new TiktokOpenVideoListParam();
        param.setCount(count);
        return page(param);
    }

    /**
     * 列出已发布的视频
     *
     * @return
     */
    public TiktokOpenVideoListResult take() {
        TiktokOpenVideoListParam param = new TiktokOpenVideoListParam();
        param.setOpenId(getOpenId())
                .setAccessToken(getAccessToken());
        return page(param);
    }

    /**
     * 列出已发布的视频
     *
     * @param cursor 分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。
     * @param count  每页数量
     * @return
     */
    public TiktokOpenVideoListResult page(Long cursor, Long count) {
        TiktokOpenVideoListParam param = new TiktokOpenVideoListParam();
        param.setCursor(cursor)
                .setCount(count)
                .setOpenId(getOpenId())
                .setAccessToken(getAccessToken());
        return page(param);
    }


    public TiktokOpenVideoListApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TiktokOpenVideoListApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    /**
     * scope
     *
     * @return
     */
    public String scope() {

        return DouyinConf.SCOPE_VIDEO_LIST;
    }


}
