package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalUserLikeResult implements Serializable {

    private String date;

    @SerializedName("new_like")
    private Integer newLike;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNewLike() {
        return newLike;
    }

    public void setNewLike(Integer newLike) {
        this.newLike = newLike;
    }
}
