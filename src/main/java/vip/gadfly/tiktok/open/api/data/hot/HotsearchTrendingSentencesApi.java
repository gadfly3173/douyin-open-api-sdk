package vip.gadfly.tiktok.open.api.data.hot;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.api.token.AccessTokenApi;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.base.ApiResponse;

public class HotsearchTrendingSentencesApi extends ApiBase {

    public static String API_URL = AppConfig.getInstance().httpUrl + "/hotsearch/trending/sentences/";

    public HotsearchSentencesResult page(HotsearchSentencesParam param) {

        AccessTokenApi accessTokenApi = new AccessTokenApi();
        String clientAccessToken = accessTokenApi.get().getAccessToken();

        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getUrlParam();
        ApiResponse response = sendGet(url);
        HotsearchSentencesResult result = response.dataToBean(HotsearchSentencesResult.class);
        return result;
    }

    public HotsearchSentencesResult take() {
        HotsearchSentencesParam param = new HotsearchSentencesParam();
        return this.page(param);
    }

    public HotsearchSentencesResult page(Long count, Long cursor) {
        HotsearchSentencesParam param = new HotsearchSentencesParam();
        param.setCount(count);
        param.setCursor(cursor);
        return this.page(param);
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
