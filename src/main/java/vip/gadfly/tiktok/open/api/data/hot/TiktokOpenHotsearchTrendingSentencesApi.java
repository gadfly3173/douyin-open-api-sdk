package vip.gadfly.tiktok.open.api.data.hot;

import vip.gadfly.tiktok.open.api.token.TiktokOpenAccessTokenApi;
import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.TiktokOpenApiResponse;

public class TiktokOpenHotsearchTrendingSentencesApi extends AbstractTiktokOpenApiBase {

    public String API_URL = getHttpUrl() + "/hotsearch/trending/sentences/";

    public TiktokOpenHotsearchSentencesResult page(TiktokOpenHotsearchSentencesParam param) {

        TiktokOpenAccessTokenApi accessTokenApi = new TiktokOpenAccessTokenApi();
        String clientAccessToken = accessTokenApi.get().getAccessToken();

        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenHotsearchSentencesResult result = response.dataToBean(TiktokOpenHotsearchSentencesResult.class);
        return result;
    }

    public TiktokOpenHotsearchSentencesResult take() {
        TiktokOpenHotsearchSentencesParam param = new TiktokOpenHotsearchSentencesParam();
        return this.page(param);
    }

    public TiktokOpenHotsearchSentencesResult page(Long count, Long cursor) {
        TiktokOpenHotsearchSentencesParam param = new TiktokOpenHotsearchSentencesParam();
        param.setCount(count);
        param.setCursor(cursor);
        return this.page(param);
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
