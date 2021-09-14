package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.base.BaseResult;

import java.util.List;

public class FansListResult extends BaseResult {

    /**
     * {
     * "data": {
     * "error_code": 0,
     * "description": "",
     * "cursor": 0,
     * "has_more": false,
     * "list": [
     * {
     * "open_id": "0da22181-d833-447f-995f-1beefea5bef3",
     * "union_id": "1ad4e099-4a0c-47d1-a410-bffb4f2f64a4",
     * "nickname": "张伟",
     * "avatar": "https://example.com/x.jpeg",
     * "city": "上海",
     * "province": "上海",
     * "country": "中国",
     * "gender": 0
     * }
     * ]
     * }
     * }
     */

    private List<OauthUserInfoResult> list;


    public List<OauthUserInfoResult> getList() {
        return list;
    }

    public void setList(List<OauthUserInfoResult> list) {
        this.list = list;
    }
}
