package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.common.TtOpBaseResult;


public class TtOpVideoUploadResult extends TtOpBaseResult {

    private TiktokOpenVideoUpload video;

    public TiktokOpenVideoUpload getVideo() {
        return video;
    }

    public void setVideo(TiktokOpenVideoUpload video) {
        this.video = video;
    }
}
