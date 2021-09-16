package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.api.video.enume.TiktokOpenVideoMediaTypeEnum;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseParam;

import java.io.File;

public class TiktokOpenVideoUploadParam extends TiktokOpenBaseParam {

    private File uploadFile;

    private String mediaType = TiktokOpenVideoMediaTypeEnum.MP4.toString();

    public File getUploadFile() {
        return uploadFile;
    }

    public TiktokOpenVideoUploadParam setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    public TiktokOpenVideoUploadParam setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }
}
