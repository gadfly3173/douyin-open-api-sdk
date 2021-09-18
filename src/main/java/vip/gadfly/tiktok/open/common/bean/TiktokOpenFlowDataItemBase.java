package vip.gadfly.tiktok.open.common.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TiktokOpenFlowDataItemBase implements Serializable {

    /**
     * 流量贡献种类
     */
    private String flow;

    /**
     * 粉丝流量贡献
     */
    @SerializedName("all_sum")
    private Long allSum;

    /**
     * 总流量贡献
     */
    @SerializedName("fans_sum")
    private Long fansSum;


    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public Long getAllSum() {
        return allSum;
    }

    public void setAllSum(Long allSum) {
        this.allSum = allSum;
    }

    public Long getFansSum() {
        return fansSum;
    }

    public void setFansSum(Long fansSum) {
        this.fansSum = fansSum;
    }
}
