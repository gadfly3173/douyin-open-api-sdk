package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.base.entity.video.BaseVideo;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * Scope: video.data
 */
public class VideoDataApi extends ApiBase {
    public static String VIDEO_DATA = AppConfig.getInstance().httpUrl + "/video/data/";

    /**
     * 查询指定视频数据
     *
     * @param param VideoDataParam
     * @return
     */
    public VideoDataResult get(VideoDataParam param) {
        String url = VIDEO_DATA + "?" + param.getUrlParam();
        String paramJson = JsonUtil.objectToJson(param.getItemIds());
        VideoDataResult result = sendPost(url, paramJson).dataToBean(VideoDataResult.class);
        return result;
    }

    /**
     * 查询指定视频数据
     *
     * @return
     */
    public BaseVideo get(String itemId) {
        BaseVideo video = null;
        VideoDataParam param = new VideoDataParam();
        param.setItemIds(new String[]{itemId});
        VideoDataResult result = get(param);
        if (result != null && result.getList().size() > 0) {
            video = result.getList().get(0);
        }
        return video;
    }

    /**
     * 列出已发布的视频
     *
     * @return
     */
    public VideoDataResult get(String[] itemIds) {
        VideoDataParam param = new VideoDataParam();
        param.setItemIds(itemIds);
        return get(param);
    }

    public VideoDataApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public VideoDataApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }


    /**
     * scope
     *
     * @return
     */
    public String scope() {

        return DouyinConf.SCOPE_VIDEO_DATA;
    }


}
