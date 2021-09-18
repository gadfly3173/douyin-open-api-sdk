package vip.gadfly.tiktok.open.api.data.star;

import vip.gadfly.tiktok.core.enums.TiktokOpenTicketType;
import vip.gadfly.tiktok.open.api.data.star.enume.TiktokOpenStarHostEnum;
import vip.gadfly.tiktok.open.base.AbstractTiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.TiktokOpenApiResponse;

public class TiktokOpenStarHostListApi extends AbstractTiktokOpenApiBase {

    public String API_URL = getHttpUrl() + "/star/hot_list/";

    public TiktokOpenStarHostListResult get(TiktokOpenStarHostListParam param) {

        String clientAccessToken = this.getTicket(TiktokOpenTicketType.CLIENT);

        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenStarHostListResult result = response.dataToBean(TiktokOpenStarHostListResult.class);
        return result;
    }

    public TiktokOpenStarHostListResult get(TiktokOpenStarHostEnum tiktokOpenStarHostEnum) {
        TiktokOpenStarHostListParam param = new TiktokOpenStarHostListParam();
        param.setHostListType(tiktokOpenStarHostEnum.getId());
        return this.get(param);
    }

    public TiktokOpenStarHostListResult get(Integer typeIndex) {
        TiktokOpenStarHostListParam param = new TiktokOpenStarHostListParam();
        param.setHostListType(typeIndex);
        return this.get(param);
    }

    public TiktokOpenStarHostListResult get() {
        TiktokOpenStarHostListParam param = new TiktokOpenStarHostListParam();
        return this.get(param);
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
