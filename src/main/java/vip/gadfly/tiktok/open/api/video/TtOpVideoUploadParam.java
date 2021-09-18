package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.api.video.enume.TiktokOpenVideoMediaTypeEnum;
import vip.gadfly.tiktok.open.common.TtOpBaseParam;

import java.io.File;

public class TtOpVideoUploadParam extends TtOpBaseParam {

    private File uploadFile;

    private String mediaType = TiktokOpenVideoMediaTypeEnum.MP4.toString();

    public File getUploadFile() {
        return uploadFile;
    }

    public TtOpVideoUploadParam setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    public TtOpVideoUploadParam setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }
}
