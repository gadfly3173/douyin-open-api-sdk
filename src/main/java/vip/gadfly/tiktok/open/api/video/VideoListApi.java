package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * Scope: video.list
 */
public class VideoListApi extends ApiBase {
    public String VIDEO_LIST = getHttpUrl() + "/video/list/";

    /**
     * 列出已发布的视频
     *
     * @param param VideoListParam
     * @return
     */
    public VideoListResult page(VideoListParam param) {
        String url = VIDEO_LIST + "?" + param.getUrlParam();
        VideoListResult result = sendGet(url).dataToBean(VideoListResult.class);
        return result;
    }

    /**
     * 列出已发布的视频
     *
     * @return
     */
    public VideoListResult take(Long count) {
        VideoListParam param = new VideoListParam();
        param.setCount(count);
        return page(param);
    }

    /**
     * 列出已发布的视频
     *
     * @return
     */
    public VideoListResult take() {
        VideoListParam param = new VideoListParam();
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
    public VideoListResult page(Long cursor, Long count) {
        VideoListParam param = new VideoListParam();
        param.setCursor(cursor)
                .setCount(count)
                .setOpenId(getOpenId())
                .setAccessToken(getAccessToken());
        return page(param);
    }


    public VideoListApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public VideoListApi withOpenId(String openId) {
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
