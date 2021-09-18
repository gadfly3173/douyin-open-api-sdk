package vip.gadfly.tiktok.open.api.data.star;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.api.data.star.enume.TiktokOpenStarHostEnum;
import vip.gadfly.tiktok.open.common.TtOpBaseParam;

public class TtOpStarHostListParam extends TtOpBaseParam {

    @SerializedName("hot_list_type")
    private Integer hostListType = TiktokOpenStarHostEnum.ONE.getId();


    public Integer getHostListType() {
        return hostListType;
    }

    public void setHostListType(Integer hostListType) {
        this.hostListType = hostListType;
    }

    /**
     * 拼接URL参数值
     *
     * @return
     */
    public String getNoPageUrlParam() {
        return super.getNoPageUrlParam() + "&hot_list_type=" + this.hostListType;
    }
}

