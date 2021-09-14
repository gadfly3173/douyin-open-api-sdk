package vip.gadfly.tiktok.open.api.user;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.BaseResult;

public class OauthUserInfoResult extends BaseResult {


    /**
     * "error_code": 0,
     * "description": "",
     * "open_id": "0da22181-d833-447f-995f-1beefea5bef3",
     * "union_id": "1ad4e099-4a0c-47d1-a410-bffb4f2f64a4",
     * "nickname": "张伟",
     * "avatar": "https://example.com/x.jpeg",
     * "city": "上海",
     * "province": "上海",
     * "country": "中国",
     * "gender": 0
     */

    @SerializedName("open_id")
    private String openId;

    @SerializedName("union_id")
    private String unionId;

    @SerializedName("nickname")
    private String nickName;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("city")
    private String city;

    @SerializedName("province")
    private String province;

    @SerializedName("country")
    private String country;

    @SerializedName("gender")
    private int gender;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
