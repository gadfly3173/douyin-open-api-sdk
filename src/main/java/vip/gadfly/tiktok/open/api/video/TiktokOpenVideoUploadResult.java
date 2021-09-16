package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;


public class TiktokOpenVideoUploadResult extends TiktokOpenBaseResult {

    private TiktokOpenVideoUpload video;

    public TiktokOpenVideoUpload getVideo() {
        return video;
    }

    public void setVideo(TiktokOpenVideoUpload video) {
        this.video = video;
    }
}
