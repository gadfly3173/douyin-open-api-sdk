package vip.gadfly.tiktok.open.api.data.star;

import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.open.api.data.star.enume.TiktokOpenStarHostEnum;
import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.common.TtOpApiResponse;

public class TtOpStarHostListApi extends AbstractTtOpApiBase {

    public String API_URL = getHttpUrl() + "/star/hot_list/";

    public TtOpStarHostListResult get(TtOpStarHostListParam param) {

        String clientAccessToken = this.getTicket(TtOpTicketType.CLIENT);

        param.setAccessToken(clientAccessToken);

        String url = API_URL + "?" + param.getNoPageUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpStarHostListResult result = response.dataToBean(TtOpStarHostListResult.class);
        return result;
    }

    public TtOpStarHostListResult get(TiktokOpenStarHostEnum tiktokOpenStarHostEnum) {
        TtOpStarHostListParam param = new TtOpStarHostListParam();
        param.setHostListType(tiktokOpenStarHostEnum.getId());
        return this.get(param);
    }

    public TtOpStarHostListResult get(Integer typeIndex) {
        TtOpStarHostListParam param = new TtOpStarHostListParam();
        param.setHostListType(typeIndex);
        return this.get(param);
    }

    public TtOpStarHostListResult get() {
        TtOpStarHostListParam param = new TtOpStarHostListParam();
        return this.get(param);
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
