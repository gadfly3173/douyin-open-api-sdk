package vip.gadfly.tiktok.open.api.data.star;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.BaseResult;

import java.util.List;

public class StarHostListResult extends BaseResult {

    @SerializedName("hot_list_update_timestamp")
    private Long hotListUpdateTimestamp;

    @SerializedName("hot_list_type")
    private Integer hotListType;

    @SerializedName("hot_list_description")
    private String hotListDescription;

    @SerializedName("list")
    private List<StarHostOneResult> list;

    public List<StarHostOneResult> getList() {
        return list;
    }

    public void setList(List<StarHostOneResult> list) {
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
