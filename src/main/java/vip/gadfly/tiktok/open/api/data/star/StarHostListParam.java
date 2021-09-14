package vip.gadfly.tiktok.open.api.data.star;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.api.data.star.enume.StarHostEnum;
import vip.gadfly.tiktok.open.base.BaseParam;

public class StarHostListParam extends BaseParam {

    @SerializedName("hot_list_type")
    private Integer hostListType = StarHostEnum.ONE.getId();


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

