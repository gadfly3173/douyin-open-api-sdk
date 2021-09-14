package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalUserCommentResult implements Serializable {

    private String date;

    @SerializedName("new_comment")
    private Integer newComment;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNewComment() {
        return newComment;
    }

    public void setNewComment(Integer newComment) {
        this.newComment = newComment;
    }
}
