package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.base.BaseResult;


public class VideoUploadResult extends BaseResult {

    private VideoUpload video;

    public VideoUpload getVideo() {
        return video;
    }

    public void setVideo(VideoUpload video) {
        this.video = video;
    }
}
