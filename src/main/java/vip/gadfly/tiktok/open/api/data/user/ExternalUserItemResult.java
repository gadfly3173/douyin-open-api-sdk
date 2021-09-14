package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalUserItemResult implements Serializable {

    private String date;

    @SerializedName("new_play")
    private Integer newPlay;

    @SerializedName("new_issue")
    private Integer newIssue;

    @SerializedName("total_issue")
    private Integer totalIssue;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNewPlay() {
        return newPlay;
    }

    public void setNewPlay(Integer newPlay) {
        this.newPlay = newPlay;
    }

    public Integer getNewIssue() {
        return newIssue;
    }

    public void setNewIssue(Integer newIssue) {
        this.newIssue = newIssue;
    }

    public Integer getTotalIssue() {
        return totalIssue;
    }

    public void setTotalIssue(Integer totalIssue) {
        this.totalIssue = totalIssue;
    }
}
