package vip.gadfly.tiktok.open.api.video;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoUpload implements Serializable {

    /**
     *
     */
    @SerializedName("video_id")
    private String videoId;

    private int width;

    private int height;


    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
