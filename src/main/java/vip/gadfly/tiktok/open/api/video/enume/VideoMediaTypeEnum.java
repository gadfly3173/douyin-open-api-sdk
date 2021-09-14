package vip.gadfly.tiktok.open.api.video.enume;

public enum VideoMediaTypeEnum {

    MP4("video/mp4"), WMV("video/wmv"), AVI("video/avi"), FLV("video/flv");
    // 成员变量
    private final String typeName;

    // 构造方法
    VideoMediaTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    // 覆盖方法
    @Override
    public String toString() {
        return this.typeName;
    }

}
