package vip.gadfly.tiktok.open.api.impl;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.open.api.TtOpVideoService;
import vip.gadfly.tiktok.open.bean.video.*;
import vip.gadfly.tiktok.open.common.ITtOpBaseService;

import static vip.gadfly.tiktok.core.enums.TtOpApiUrl.Video.*;

/**
 * @author Gadfly
 * @since 2021-09-30 15:01
 */
@Slf4j
@RequiredArgsConstructor
public class TtOpVideoServiceImpl implements TtOpVideoService {
    private final ITtOpBaseService ttOpBaseService;

    protected TtOpConfigStorage getTtOpConfigStorage() {
        return this.ttOpBaseService.getTtOpConfigStorage();
    }

    @Override
    public TtOpTiktokVideoCreateResult createTiktokVideo(String openId, TtOpTiktokVideoCreateRequest request) {
        log.debug("创建抖音视频，收到的参数：openId={}, request={}", openId, request);
        String url = String.format(CREATE_TIKTOK_VIDEO_URL.getUrl(getTtOpConfigStorage()),
                openId, this.ttOpBaseService.getAccessToken(openId));
        log.debug("url={}， request={}", url, request);
        return this.ttOpBaseService.post(url, request, TtOpTiktokVideoCreateResult.class);
    }

    @Override
    public TtOpTiktokVideoUploadResult uploadTiktokVideo(String openId, TtOpTiktokVideoUploadRequest request) {
        log.debug("上传抖音视频，收到的参数：openId={}, request={}", openId, request);
        String url = String.format(UPLOAD_TIKTOK_VIDEO_URL.getUrl(getTtOpConfigStorage()),
                openId, this.ttOpBaseService.getAccessToken(openId));
        Multimap<String, String> headers = LinkedListMultimap.create();
        headers.put("Content-Type", "multipart/form-data");
        log.debug("url={}, headers={}", url, headers);
        return this.ttOpBaseService.postWithHeaders(url, headers, request, TtOpTiktokVideoUploadResult.class);
    }

    @Override
    public TtOpTiktokVideoDataResult getTiktokSpecificVideoData(String openId, TtOpTiktokVideoDataRequest request) {
        log.debug("查询抖音指定视频数据，收到的参数：openId={}, request={}", openId, request);
        String url = String.format(GET_TIKTOK_SPECIFIC_VIDEO_DATA_URL.getUrl(getTtOpConfigStorage()),
                openId, this.ttOpBaseService.getAccessToken(openId));
        log.debug("url={}, request={}", url, request);
        return this.ttOpBaseService.post(url, request, TtOpTiktokVideoDataResult.class);
    }
}
