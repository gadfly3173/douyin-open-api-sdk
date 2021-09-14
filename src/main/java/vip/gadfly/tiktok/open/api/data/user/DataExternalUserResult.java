package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.BaseResult;

import java.util.List;

public class DataExternalUserResult<T> extends BaseResult {

    @SerializedName("result_list")
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
