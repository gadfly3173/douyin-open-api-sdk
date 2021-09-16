package vip.gadfly.tiktok.open.api.data.discovery;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TiktokOpenEntRankItem implements Serializable {


    private String id;
    private String name;

    @SerializedName("name_en")
    private String nameEn;

    private String poster;

    @SerializedName("release_date")
    private String releaseDate;

    private String hot;

    private String type;

    @SerializedName("maoyan_id")
    private String maoyanId;

    private List<String> areas;

    private List<String> tags;

    private List<String> directors;

    private List<String> actors;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaoyanId() {
        return maoyanId;
    }

    public void setMaoyanId(String maoyanId) {
        this.maoyanId = maoyanId;
    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}
