package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.common.bean.video.TtOpBaseVideo;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * Scope: video.data
 */
public class TtOpVideoDataApi extends AbstractTtOpApiBase {
    public String VIDEO_DATA = getHttpUrl() + "/video/data/";

    /**
     * 查询指定视频数据
     *
     * @param param TtOpVideoDataParam
     * @return
     */
    public TtOpVideoDataResult getVideoResult(TtOpVideoDataParam param) {
        String url = VIDEO_DATA + "?" + param.getUrlParam();
        String paramJson = JsonUtil.objectToJson(param.getItemIds());
        TtOpVideoDataResult result = sendPost(url, paramJson).dataToBean(TtOpVideoDataResult.class);
        return result;
    }

    /**
     * 查询指定视频数据
     *
     * @return
     */
    public TtOpBaseVideo getBaseVideo(String itemId) {
        TtOpBaseVideo video = null;
        TtOpVideoDataParam param = new TtOpVideoDataParam();
        param.setItemIds(new String[]{itemId});
        TtOpVideoDataResult result = getVideoResult(param);
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
    public TtOpVideoDataResult getVideoResult(String[] itemIds) {
        TtOpVideoDataParam param = new TtOpVideoDataParam();
        param.setItemIds(itemIds);
        return getVideoResult(param);
    }

    public TtOpVideoDataApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TtOpVideoDataApi withOpenId(String openId) {
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
