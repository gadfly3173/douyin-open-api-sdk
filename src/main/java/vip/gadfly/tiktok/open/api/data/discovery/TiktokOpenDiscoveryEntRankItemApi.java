package vip.gadfly.tiktok.open.api.data.discovery;

import vip.gadfly.tiktok.core.enums.TiktokOpenTicketType;
import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.TiktokOpenApiResponse;

public class TiktokOpenDiscoveryEntRankItemApi extends AbstractTiktokOpenApiBase {

    public String API_URL = getHttpUrl() + "/discovery/ent/rank/item";

    public TiktokOpenDiscoveryEntRankItemResult get() {

        String clientAccessToken = this.getTicket(TiktokOpenTicketType.CLIENT);

        TiktokOpenDiscoveryEntRankItemParam param = new TiktokOpenDiscoveryEntRankItemParam();
        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenDiscoveryEntRankItemResult result = response.dataToBean(TiktokOpenDiscoveryEntRankItemResult.class);
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
