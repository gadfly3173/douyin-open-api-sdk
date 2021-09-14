package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalUserFansResult implements Serializable {

    private String date;

    @SerializedName("total_fans")
    private Integer totalFans;

    @SerializedName("new_fans")
    private Integer newFans;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTotalFans() {
        return totalFans;
    }

    public void setTotalFans(Integer totalFans) {
        this.totalFans = totalFans;
    }

    public Integer getNewFans() {
        return newFans;
    }

    public void setNewFans(Integer newFans) {
        this.newFans = newFans;
    }
}
