package vip.gadfly.tiktok.open.api.data.user;

import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.open.api.data.user.enume.DataExternalUserEnum;
import vip.gadfly.tiktok.open.base.ApiBase;
import vip.gadfly.tiktok.open.base.ApiResponse;
import vip.gadfly.tiktok.open.conf.DouyinConf;

/**
 * 获取用户评论数
 */

public class DataExternalUserApi extends ApiBase {

    public static String API_URL = AppConfig.getInstance().httpUrl + "/data/external/user/";
//    private static String DEEP_RESULT_LIST = "result_list";


    public DataExternalUserResult<?> get(Integer day, DataExternalUserEnum dataTypeEnum) {
        DataExternalUserParam param = new DataExternalUserParam();
        param.setDateType(day);
        param.setDataType(dataTypeEnum);
        return this.get(param);
    }

    public DataExternalUserResult<?> get() {
        DataExternalUserParam param = new DataExternalUserParam();
        return this.get(param);
    }

    public DataExternalUserResult<?> get(DataExternalUserParam param) {
        param.setAccessToken(getAccessToken());
        param.setOpenId(getOpenId());

        String typeName = param.getDataType().typeName();
        Class<?> resultClass = param.getDataType().resultClass();

        String url = API_URL + typeName + "?" + param.getUrlParam();
        ApiResponse response = sendGet(url);
        DataExternalUserResult<?> result = response.dataToBean(DataExternalUserResult.class);
        return result;
    }


    @Override
    public DataExternalUserApi withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    @Override
    public DataExternalUserApi withOpenId(String openId) {
        setOpenId(openId);
        return this;
    }

    @Override
    public String scope() {
        return DouyinConf.SCOPE_DATA_EXTERNAL_USER;
    }
}
