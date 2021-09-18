package vip.gadfly.tiktok.open.api.data.user;

import vip.gadfly.tiktok.open.api.data.user.enume.TiktokOpenDataExternalUserEnum;
import vip.gadfly.tiktok.open.common.AbstractTtOpApiBase;
import vip.gadfly.tiktok.open.common.TtOpApiResponse;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * 获取用户评论数
 */

public class TtOpDataExternalUserApi extends AbstractTtOpApiBase {

    public String API_URL = getHttpUrl() + "/data/external/user/";
//    private static String DEEP_RESULT_LIST = "result_list";


    public TtOpDataExternalUserResult<?> get(Integer day, TiktokOpenDataExternalUserEnum dataTypeEnum) {
        TtOpDataExternalUserParam param = new TtOpDataExternalUserParam();
        param.setDateType(day);
        param.setDataType(dataTypeEnum);
        return this.get(param);
    }

    public TtOpDataExternalUserResult<?> get() {
        TtOpDataExternalUserParam param = new TtOpDataExternalUserParam();
        return this.get(param);
    }

    public TtOpDataExternalUserResult<?> get(TtOpDataExternalUserParam param) {
        param.setAccessToken(getAccessToken());
        param.setOpenId(getOpenId());

        String typeName = param.getDataType().typeName();
        Class<?> resultClass = param.getDataType().resultClass();

        String url = API_URL + typeName + "?" + param.getUrlParam();
        TtOpApiResponse response = sendGet(url);
        TtOpDataExternalUserResult<?> result = response.dataToBean(TtOpDataExternalUserResult.class);
        return result;
    }


    @Override
    public TtOpDataExternalUserApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    @Override
    public TtOpDataExternalUserApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    @Override
    public String scope() {
        return DouyinConf.SCOPE_DATA_EXTERNAL_USER;
    }
}
