package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * Scope: video.create
 */
public class TtOpVideoCreateApi extends AbstractTtOpApiBase {
    public String VIDEO_UPLOAD = getHttpUrl() + "/video/create/";

    /**
     * 创建抖音视频
     *
     * @param param TtOpVideoCreateParam
     * @return
     */
    public TiktokOpenVideoCreateResponse doPost(TtOpVideoCreateParam param) {
        String url = VIDEO_UPLOAD + "?" + param.getNoPageUrlParam();
        String paramJson = JsonUtil.objectToJson(param);
        TiktokOpenVideoCreateResponse result = sendPost(url, paramJson).dataToBean(TiktokOpenVideoCreateResponse.class);
        return result;
    }

    /**
     * 根据 videoId + text 创建视频
     *
     * @param videoId
     * @param text
     * @return
     */
    public TiktokOpenVideoCreateResponse doPost(String videoId, String text) {
        TtOpVideoCreateParam param = new TtOpVideoCreateParam();
        param.setVideoId(videoId).setText(text);
        return doPost(param);
    }

    public TtOpVideoCreateApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TtOpVideoCreateApi withOpenId(String openId) {
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
