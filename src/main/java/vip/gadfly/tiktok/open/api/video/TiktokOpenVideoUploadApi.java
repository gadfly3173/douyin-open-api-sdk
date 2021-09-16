package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.base.TiktokOpenApiBase;
import vip.gadfly.tiktok.open.conf.DouyinConf;

import java.io.File;

/**
 * 调用/video/upload/上传文件到文件务器, 得到视频文件video_id。
 * <p>
 * 为了更好的观看体验，推荐上传16:9，分辨率为720p（1280x720）及以上的竖版视频。
 * 支持常用视频格式，推荐使用 mp4 、webm。
 * 视频文件大小不超过300M，时长在1分钟以内。
 * 带品牌logo或品牌水印的视频，会命中抖音的审核逻辑，有比较大的概率导致分享视频推荐降权处理/分享视频下架处理/分享账号被封禁处理。强烈建议第三方应用自行处理好分享内容中的不合规水印。
 * 视频审核逻辑与端上一致。
 * 调用/video/create/正式创建抖音视频, 创建抖音视频后, 会有一个审核过程, 期间只有自己可见。
 * <p>
 * 如果发布视频想@用户，需要获取昵称与open_id，可以调用以下几个接口
 * <p>
 * 粉丝列表
 * 关注列表
 */
public class TiktokOpenVideoUploadApi extends TiktokOpenApiBase {
    public String VIDEO_UPLOAD = getHttpUrl() + "/video/upload/";

    /**
     * 上传文件到文件务器
     *
     * @param param TiktokOpenVideoUploadParam
     * @return
     */
    public TiktokOpenVideoUploadResult doPost(TiktokOpenVideoUploadParam param) {
        String url = VIDEO_UPLOAD + "?" + param.getNoPageUrlParam();
        TiktokOpenVideoUploadResult result = sendPost(url, param.getUploadFile(), param.getMediaType()).dataToBean(TiktokOpenVideoUploadResult.class);
        return result;
    }

    /**
     * 指定 openId、file、mediaType 上传文件到文件务器
     *
     * @return
     */
    public TiktokOpenVideoUploadResult doPost(File uploadVideoFile, String videoMediaType) {
        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TiktokOpenVideoUploadParam param = new TiktokOpenVideoUploadParam();
        param.setUploadFile(uploadVideoFile)
                .setMediaType(videoMediaType)
                .setOpenId(getOpenId())
                .setAccessToken(accessToken);
        return doPost(param);
    }


    /**
     * 指定 videoDir、mediaType 上传文件到文件务器
     *
     * @return
     */
    public TiktokOpenVideoUploadResult doPost(String videoDir, String videoMediaType) {

        //取缓存token
        String accessToken = getAccessToken();
        //拼接参数
        TiktokOpenVideoUploadParam param = new TiktokOpenVideoUploadParam();
        param.setUploadFile(new File(videoDir))
                .setMediaType(videoMediaType)
                .setOpenId(getOpenId())
                .setAccessToken(accessToken);
        return doPost(param);
    }


    public TiktokOpenVideoUploadApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public TiktokOpenVideoUploadApi withOpenId(String openId) {
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
