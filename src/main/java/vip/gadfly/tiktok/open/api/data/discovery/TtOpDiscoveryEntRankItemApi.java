package vip.gadfly.tiktok.open.api.data.discovery;

import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.common.TtOpApiResponse;

public class TtOpDiscoveryEntRankItemApi extends AbstractTtOpApiBase {

    public String API_URL = getHttpUrl() + "/discovery/ent/rank/item";

    public TtOpDiscoveryEntRankItemResult get() {

        String clientAccessToken = this.getTicket(TtOpTicketType.CLIENT);

        TtOpDiscoveryEntRankItemParam param = new TtOpDiscoveryEntRankItemParam();
        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpDiscoveryEntRankItemResult result = response.dataToBean(TtOpDiscoveryEntRankItemResult.class);
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
