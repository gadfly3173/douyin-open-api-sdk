package vip.gadfly.tiktok.open.api.data.discovery;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.api.data.discovery.enume.TiktokOpenDiscoveryEntEnum;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseParam;

public class TiktokOpenDiscoveryEntRankItemParam extends TiktokOpenBaseParam {

    @SerializedName("type")
    private Integer type = TiktokOpenDiscoveryEntEnum.ONE.getId();

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

