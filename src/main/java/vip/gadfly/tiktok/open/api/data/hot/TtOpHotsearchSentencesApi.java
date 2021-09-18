package vip.gadfly.tiktok.open.api.data.hot;

import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.common.TtOpApiResponse;

public class TtOpHotsearchSentencesApi extends AbstractTtOpApiBase {

    public String API_URL = getHttpUrl() + "/hotsearch/sentences/";

    public TtOpHotsearchSentencesResult get() {

        String clientAccessToken = this.getTicket(TtOpTicketType.CLIENT);

        TtOpHotsearchSentencesParam param = new TtOpHotsearchSentencesParam();
        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpHotsearchSentencesResult result = response.dataToBean(TtOpHotsearchSentencesResult.class);
        return result;
    }

    @Override
    public AbstractTtOpApiBase withAccessToken(String accessToken) {
        return null;
    }

    @Override
    public AbstractTtOpApiBase withOpenId(String openId) {
        return null;
    }

    @Override
    public String scope() {
        return null;
    }
}
