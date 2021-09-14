package vip.gadfly.tiktok.open.api.user;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.entity.FlowDataItemBase;
import vip.gadfly.tiktok.open.base.entity.StaDataItemBase;

import java.io.Serializable;
import java.util.List;

public class FansDataDetailResult implements Serializable {

    /**

     {
     "data":{
     "error_code":0,
     "description":"",
     "fans_data":{
     "all_fans_num":10000,
     "gender_distributions":Array[2],
     "age_distributions":Array[5],
     "geographical_distributions":Array[2],
     "active_days_distributions":Array[4],
     "device_distributions":Array[2],
     "interest_distributions":Array[3],
     "flow_contributions":Array[4]
     }
     }
     }

     */

    /**
     * 所有粉丝的数量
     */
    @SerializedName("all_fans_num")
    private Long allFansNum;

    /**
     * 粉丝性别分布 item: ["1","2"] (男:1,女:2)
     * <p>
     * item*	string
     * 分布的种类
     * <p>
     * value*	integer
     * 分布的数值
     */
    @SerializedName("gender_distributions")
    private List<StaDataItemBase> genderDistributions;

    /**
     * 粉丝年龄分布 item: ["<=25","26-32","33-39","40-46",">46"]
     * <p>
     * item*	string
     * 分布的种类
     * <p>
     * value*	integer
     * 分布的数值
     */
    @SerializedName("age_distributions")
    private List<StaDataItemBase> ageDistributions;

    /**
     * 粉丝地域分布 item: ["北京","福建","香港"...]
     * item*	string
     * 分布的种类
     * <p>
     * value*	integer
     * 分布的数值
     */
    @SerializedName("geographical_distributions")
    private List<StaDataItemBase> geographicalDistributions;

    /**
     * 粉丝活跃天数分布 item: ["0-1","2-5","6-10","11-31"]
     * item*	string
     * 分布的种类
     * <p>
     * value*	integer
     * 分布的数值
     */
    @SerializedName("active_days_distributions")
    private List<StaDataItemBase> activeDaysDistributions;

    /**
     * 粉丝设备分布 item: ["苹果","华为","三星","小米"...]
     * item*	string
     * 分布的种类
     * <p>
     * value*	integer
     * 分布的数值
     */
    @SerializedName("device_distributions")
    private List<StaDataItemBase> deviceDistributions;

    /**
     * 粉丝兴趣分布 item: ["生活"","美食","旅行"...]
     * item*	string
     * 分布的种类
     * <p>
     * value*	integer
     * 分布的数值
     */
    @SerializedName("interest_distributions")
    private List<StaDataItemBase> interestDistributions;

    /**
     * 粉丝流量贡献 flow: ["vv","like_cnt","comment_cnt","share_video_cnt"]
     */
    @SerializedName("flow_contributions")
    private List<FlowDataItemBase> flowContributions;


    public Long getAllFansNum() {
        return allFansNum;
    }

    public void setAllFansNum(Long allFansNum) {
        this.allFansNum = allFansNum;
    }

    public List<StaDataItemBase> getGenderDistributions() {
        return genderDistributions;
    }

    public void setGenderDistributions(List<StaDataItemBase> genderDistributions) {
        this.genderDistributions = genderDistributions;
    }

    public List<StaDataItemBase> getAgeDistributions() {
        return ageDistributions;
    }

    public void setAgeDistributions(List<StaDataItemBase> ageDistributions) {
        this.ageDistributions = ageDistributions;
    }

    public List<StaDataItemBase> getGeographicalDistributions() {
        return geographicalDistributions;
    }

    public void setGeographicalDistributions(List<StaDataItemBase> geographicalDistributions) {
        this.geographicalDistributions = geographicalDistributions;
    }

    public List<StaDataItemBase> getActiveDaysDistributions() {
        return activeDaysDistributions;
    }

    public void setActiveDaysDistributions(List<StaDataItemBase> activeDaysDistributions) {
        this.activeDaysDistributions = activeDaysDistributions;
    }

    public List<StaDataItemBase> getDeviceDistributions() {
        return deviceDistributions;
    }

    public void setDeviceDistributions(List<StaDataItemBase> deviceDistributions) {
        this.deviceDistributions = deviceDistributions;
    }

    public List<StaDataItemBase> getInterestDistributions() {
        return interestDistributions;
    }

    public void setInterestDistributions(List<StaDataItemBase> interestDistributions) {
        this.interestDistributions = interestDistributions;
    }

    public List<FlowDataItemBase> getFlowContributions() {
        return flowContributions;
    }

    public void setFlowContributions(List<FlowDataItemBase> flowContributions) {
        this.flowContributions = flowContributions;
    }
}
