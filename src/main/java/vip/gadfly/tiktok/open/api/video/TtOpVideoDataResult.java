package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.common.TtOpBaseResult;
import vip.gadfly.tiktok.open.common.bean.video.TtOpBaseVideo;

import java.util.List;

public class TtOpVideoDataResult extends TtOpBaseResult {

    private List<TtOpBaseVideo> list;

    public List<TtOpBaseVideo> getList() {
        return list;
    }

    public void setList(List<TtOpBaseVideo> list) {
        this.list = list;
    }
}

