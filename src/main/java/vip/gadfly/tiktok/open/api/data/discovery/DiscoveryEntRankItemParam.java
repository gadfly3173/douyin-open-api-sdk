package vip.gadfly.tiktok.open.api.data.discovery;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.api.data.discovery.enume.DiscoveryEntEnum;
import vip.gadfly.tiktok.open.base.BaseParam;

public class DiscoveryEntRankItemParam extends BaseParam {

    @SerializedName("type")
    private Integer type = DiscoveryEntEnum.ONE.getId();

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getNoPageUrlParam() {
        return super.getNoPageUrlParam() + "&type=" + this.type;
    }
}

