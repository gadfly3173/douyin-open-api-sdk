package vip.gadfly.tiktok.open.api.data.user;

import vip.gadfly.tiktok.open.api.data.user.enume.TiktokOpenDataExternalUserEnum;
import vip.gadfly.tiktok.open.base.TiktokOpenApiBase;
import vip.gadfly.tiktok.open.base.TiktokOpenApiResponse;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * 获取用户评论数
 */

public class TiktokOpenDataExternalUserApi extends TiktokOpenApiBase {

    public String API_URL = getHttpUrl() + "/data/external/user/";
//    private static String DEEP_RESULT_LIST = "result_list";


    public TiktokOpenDataExternalUserResult<?> get(Integer day, TiktokOpenDataExternalUserEnum dataTypeEnum) {
        TiktokOpenDataExternalUserParam param = new TiktokOpenDataExternalUserParam();
        param.setDateType(day);
        param.setDataType(dataTypeEnum);
        return this.get(param);
    }

    public TiktokOpenDataExternalUserResult<?> get() {
        TiktokOpenDataExternalUserParam param = new TiktokOpenDataExternalUserParam();
        return this.get(param);
    }

    public TiktokOpenDataExternalUserResult<?> get(TiktokOpenDataExternalUserParam param) {
        param.setAccessToken(getAccessToken());
        param.setOpenId(getOpenId());

        String typeName = param.getDataType().typeName();
        Class<?> resultClass = param.getDataType().resultClass();

        String url = API_URL + typeName + "?" + param.getUrlParam();
        TiktokOpenApiResponse response = sendGet(url);
        TiktokOpenDataExternalUserResult<?> result = response.dataToBean(TiktokOpenDataExternalUserResult.class);
        return result;
    }


    @Override
    public TiktokOpenDataExternalUserApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    @Override
    public TiktokOpenDataExternalUserApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    @Override
    public String scope() {
        return DouyinConf.SCOPE_DATA_EXTERNAL_USER;
    }
}
