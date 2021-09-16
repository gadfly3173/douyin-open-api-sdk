package vip.gadfly.tiktok.open.api.data.star;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;

import java.util.List;

public class TiktokOpenStarHostListResult extends TiktokOpenBaseResult {

    @SerializedName("hot_list_update_timestamp")
    private Long hotListUpdateTimestamp;

    @SerializedName("hot_list_type")
    private Integer hotListType;

    @SerializedName("hot_list_description")
    private String hotListDescription;

    @SerializedName("list")
    private List<TiktokOpenStarHostOneResult> list;

    public List<TiktokOpenStarHostOneResult> getList() {
        return list;
    }

    public void setList(List<TiktokOpenStarHostOneResult> list) {
        this.list = list;
    }

    public Long getHotListUpdateTimestamp() {
        return hotListUpdateTimestamp;
    }

    public void setHotListUpdateTimestamp(Long hotListUpdateTimestamp) {
        this.hotListUpdateTimestamp = hotListUpdateTimestamp;
    }

    public Integer getHotListType() {
        return hotListType;
    }

    public void setHotListType(Integer hotListType) {
        this.hotListType = hotListType;
    }

    public String getHotListDescription() {
        return hotListDescription;
    }

    public void setHotListDescription(String hotListDescription) {
        this.hotListDescription = hotListDescription;
    }
}
