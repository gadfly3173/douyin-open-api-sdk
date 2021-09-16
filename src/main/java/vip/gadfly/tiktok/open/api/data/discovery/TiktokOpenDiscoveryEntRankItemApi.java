package vip.gadfly.tiktok.open.api.data.discovery;

import vip.gadfly.tiktok.open.api.token.TiktokOpenAccessTokenApi;
import vip.gadfly.tiktok.open.base.TiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.TiktokOpenApiResponse;

public class TiktokOpenDiscoveryEntRankItemApi extends TiktokOpenApiBase {

    public String API_URL = getHttpUrl() + "/discovery/ent/rank/item";

    public TiktokOpenDiscoveryEntRankItemResult get() {

        TiktokOpenAccessTokenApi accessTokenApi = new TiktokOpenAccessTokenApi();
        String clientAccessToken = accessTokenApi.get().getAccessToken();

        TiktokOpenDiscoveryEntRankItemParam param = new TiktokOpenDiscoveryEntRankItemParam();
        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenDiscoveryEntRankItemResult result = response.dataToBean(TiktokOpenDiscoveryEntRankItemResult.class);
        return result;
    }

    @Override
    public TiktokOpenApiBase withAccessToken(String accessToken) {
        return null;
    }

    @Override
    public TiktokOpenApiBase withOpenId(String openId) {
        return null;
    }

    @Override
    public String scope() {
        return null;
    }
}
