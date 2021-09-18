package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.common.TtOpBaseResult;

import java.util.List;

public class TtOpDataExternalUserResult<T> extends TtOpBaseResult {

    @SerializedName("result_list")
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
