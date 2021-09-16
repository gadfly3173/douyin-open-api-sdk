package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;
import vip.gadfly.tiktok.open.base.entity.video.TiktokOpenBaseVideo;

import java.util.List;

public class TiktokOpenVideoDataResult extends TiktokOpenBaseResult {

    private List<TiktokOpenBaseVideo> list;

    public List<TiktokOpenBaseVideo> getList() {
        return list;
    }

    public void setList(List<TiktokOpenBaseVideo> list) {
        this.list = list;
    }
}

