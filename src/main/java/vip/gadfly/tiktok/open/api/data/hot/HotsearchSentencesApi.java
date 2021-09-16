package vip.gadfly.tiktok.open.api.data.hot;

import vip.gadfly.tiktok.open.api.token.AccessTokenApi;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.base.ApiResponse;

public class HotsearchSentencesApi extends ApiBase {

    public String API_URL = getHttpUrl() + "/hotsearch/sentences/";

    public HotsearchSentencesResult get() {

        AccessTokenApi accessTokenApi = new AccessTokenApi();
        String clientAccessToken = accessTokenApi.get().getAccessToken();

        HotsearchSentencesParam param = new HotsearchSentencesParam();
        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        ApiResponse response = sendGet(url);
        HotsearchSentencesResult result = response.dataToBean(HotsearchSentencesResult.class);
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
