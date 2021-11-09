package vip.gadfly.tiktok.open.api;

import vip.gadfly.tiktok.open.bean.video.*;

/**
 * @author Gadfly
 * @since 2021-09-30 10:27
 */
public interface TtOpVideoService {
    /**
     * 创建抖音视频
     *
     * @param openId  用户openid
     * @param request 请求
     * @return 结果
     */
    TtOpTiktokVideoCreateResult createTiktokVideo(String openId, TtOpTiktokVideoCreateRequest request);

    /**
     * 上传抖音视频
     *
     * @param openId  用户openid
     * @param request 视频文件
     * @return 结果
     */
    TtOpTiktokVideoUploadResult uploadTiktokVideo(String openId, TtOpTiktokVideoUploadRequest request);

    /**
     * 查询抖音指定视频数据
     *
     * @param openId  用户openid
     * @param request 视频id list
     * @return 结果
     */
    TtOpTiktokVideoDataResult getTiktokSpecificVideoData(String openId, TtOpTiktokVideoDataRequest request);
}
