package vip.gadfly.tiktok.open.api.data.star;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.api.data.star.enume.StarHostEnum;
import vip.gadfly.tiktok.open.api.token.AccessTokenApi;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.base.ApiResponse;

public class StarHostListApi extends ApiBase {

    public String API_URL = getHttpUrl() + "/star/hot_list/";

    public StarHostListResult get(StarHostListParam param) {

        AccessTokenApi accessTokenApi = new AccessTokenApi();
        String clientAccessToken = accessTokenApi.get().getAccessToken();

        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        ApiResponse response = sendGet(url);
        StarHostListResult result = response.dataToBean(StarHostListResult.class);
        return result;
    }

    public StarHostListResult get(StarHostEnum starHostEnum) {
        StarHostListParam param = new StarHostListParam();
        param.setHostListType(starHostEnum.getId());
        return this.get(param);
    }

    public StarHostListResult get(Integer typeIndex) {
        StarHostListParam param = new StarHostListParam();
        param.setHostListType(typeIndex);
        return this.get(param);
    }

    public StarHostListResult get() {
        StarHostListParam param = new StarHostListParam();
        return this.get(param);
    }

    @Override
    public ApiBase withAccessToken(String accessToken) {
        return null;
    }

    @Override
    public ApiBase withOpenId(String openId) {
        return null;
    }

    @Override
    public String scope() {
        return null;
    }
}
