package vip.gadfly.tiktok.open.api.data.user;

import com.google.gson.annotations.SerializedName;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;

import java.util.List;

public class TiktokOpenDataExternalUserResult<T> extends TiktokOpenBaseResult {

    @SerializedName("result_list")
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
