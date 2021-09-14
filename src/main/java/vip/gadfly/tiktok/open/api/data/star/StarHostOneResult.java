package vip.gadfly.tiktok.open.api.data.star;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StarHostOneResult implements Serializable {

    @SerializedName("rank")
    private Long rank;

    @SerializedName("follower")
    private Integer follower;

    @SerializedName("nick_name")
    private String nickName;

    private List<String> tags;

    private Double score;

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
