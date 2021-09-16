package vip.gadfly.tiktok.open.api.data.hot;

import vip.gadfly.tiktok.open.api.token.TiktokOpenAccessTokenApi;
import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.TiktokOpenApiResponse;

public class TiktokOpenHotsearchSentencesApi extends AbstractTiktokOpenApiBase {

    public String API_URL = getHttpUrl() + "/hotsearch/sentences/";

    public TiktokOpenHotsearchSentencesResult get() {

        TiktokOpenAccessTokenApi accessTokenApi = new TiktokOpenAccessTokenApi();
        String clientAccessToken = accessTokenApi.get().getAccessToken();

        TiktokOpenHotsearchSentencesParam param = new TiktokOpenHotsearchSentencesParam();
        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenHotsearchSentencesResult result = response.dataToBean(TiktokOpenHotsearchSentencesResult.class);
        return result;
    }

    @Override
    public AbstractTiktokOpenApiBase withAccessToken(String accessToken) {
        return null;
    }

    @Override
    public AbstractTiktokOpenApiBase withOpenId(String openId) {
        return null;
    }

    @Override
    public String scope() {
        return null;
    }
}
