package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TiktokOpenExternalUserShareResult implements Serializable {

    private String date;

    @SerializedName("new_share")
    private Integer newShare;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
