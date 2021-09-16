package vip.gadfly.tiktok.open.api.data.discovery;

import vip.gadfly.tiktok.open.api.token.AccessTokenApi;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.base.ApiResponse;

public class DiscoveryEntRankItemApi extends ApiBase {

    public String API_URL = getHttpUrl() + "/discovery/ent/rank/item";

    public DiscoveryEntRankItemResult get() {

        AccessTokenApi accessTokenApi = new AccessTokenApi();
        String clientAccessToken = accessTokenApi.get().getAccessToken();

        DiscoveryEntRankItemParam param = new DiscoveryEntRankItemParam();
        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        ApiResponse response = sendGet(url);
        DiscoveryEntRankItemResult result = response.dataToBean(DiscoveryEntRankItemResult.class);
        return result;
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
