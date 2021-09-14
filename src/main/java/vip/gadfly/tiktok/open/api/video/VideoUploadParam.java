package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.api.video.enume.VideoMediaTypeEnum;
import vip.gadfly.tiktok.open.base.BaseParam;

import java.io.File;

public class VideoUploadParam extends BaseParam {

    private File uploadFile;

    private String mediaType = VideoMediaTypeEnum.MP4.toString();

    public File getUploadFile() {
        return uploadFile;
    }

    public VideoUploadParam setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    public VideoUploadParam setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }
}
