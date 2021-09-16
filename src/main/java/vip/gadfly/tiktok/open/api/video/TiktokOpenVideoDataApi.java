package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.entity.video.TiktokOpenBaseVideo;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * Scope: video.data
 */
public class TiktokOpenVideoDataApi extends AbstractTiktokOpenApiBase {
    public String VIDEO_DATA = getHttpUrl() + "/video/data/";

    /**
     * 查询指定视频数据
     *
     * @param param TiktokOpenVideoDataParam
     * @return
     */
    public TiktokOpenVideoDataResult get(TiktokOpenVideoDataParam param) {
        String url = VIDEO_DATA + "?" + param.getUrlParam();
        String paramJson = JsonUtil.objectToJson(param.getItemIds());
        TiktokOpenVideoDataResult result = sendPost(url, paramJson).dataToBean(TiktokOpenVideoDataResult.class);
        return result;
    }

    /**
     * 查询指定视频数据
     *
     * @return
     */
    public TiktokOpenBaseVideo get(String itemId) {
        TiktokOpenBaseVideo video = null;
        TiktokOpenVideoDataParam param = new TiktokOpenVideoDataParam();
        param.setItemIds(new String[]{itemId});
        TiktokOpenVideoDataResult result = get(param);
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
    public TiktokOpenVideoDataResult get(String[] itemIds) {
        TiktokOpenVideoDataParam param = new TiktokOpenVideoDataParam();
        param.setItemIds(itemIds);
        return get(param);
    }

    public TiktokOpenVideoDataApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TiktokOpenVideoDataApi withOpenId(String openId) {
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
