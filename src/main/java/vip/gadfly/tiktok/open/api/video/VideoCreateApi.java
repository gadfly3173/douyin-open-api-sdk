package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * Scope: video.create
 */
public class VideoCreateApi extends ApiBase {
    public String VIDEO_UPLOAD = getHttpUrl() + "/video/create/";

    /**
     * 创建抖音视频
     *
     * @param param VideoCreateParam
     * @return
     */
    public VideoCreateResponse doPost(VideoCreateParam param) {
        String url = VIDEO_UPLOAD + "?" + param.getNoPageUrlParam();
        String paramJson = JsonUtil.objectToJson(param);
        VideoCreateResponse result = sendPost(url, paramJson).dataToBean(VideoCreateResponse.class);
        return result;
    }

    /**
     * 根据 videoId + text 创建视频
     *
     * @param videoId
     * @param text
     * @return
     */
    public VideoCreateResponse doPost(String videoId, String text) {
        VideoCreateParam param = new VideoCreateParam();
        param.setVideoId(videoId).setText(text);
        return doPost(param);
    }

    public VideoCreateApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public VideoCreateApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    /**
     * scope
     *
     * @return
     */
    public String scope() {

        return DouyinConf.SCOPE_VIDEO_CREATE;
    }

}
