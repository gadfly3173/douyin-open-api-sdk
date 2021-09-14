package vip.gadfly.tiktok.open.api.video;

import vip.gadfly.tiktok.open.base.BaseResult;
import vip.gadfly.tiktok.open.base.entity.video.BaseVideo;

import java.util.List;

public class VideoDataResult extends BaseResult {

    private List<BaseVideo> list;

    public List<BaseVideo> getList() {
        return list;
    }

    public void setList(List<BaseVideo> list) {
        this.list = list;
    }
}

