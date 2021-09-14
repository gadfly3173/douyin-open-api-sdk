package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalUserProfileResult implements Serializable {

    private String date;

    @SerializedName("profile_uv")
    private Integer profileUv;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
