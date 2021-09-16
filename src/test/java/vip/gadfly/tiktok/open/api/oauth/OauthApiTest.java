package vip.gadfly.tiktok.open.api.oauth;

import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.api.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class OauthApiTest extends BaseTest {

    @Test
    public void getOauthConnectUrl() {
        TiktokOpenOauthApi oauthApi = new TiktokOpenOauthApi();
        String result = oauthApi.getScanUrl();
        System.out.println(result);
        Assert.assertNotNull(result);
    }


    @Test
    public void getOauthConnectResult() {
        TiktokOpenOauthApi oauthApi = new TiktokOpenOauthApi();
        TiktokOpenOauthConnectResult result = new TiktokOpenOauthConnectResult();
        result.setCode("00");
        result.setState("22");
        TiktokOpenOauthConnectResult result1 = oauthApi.getResult(JsonUtil.objectToJson(result));
        System.out.println(JsonUtil.objectToJson(result1));
        Assert.assertNotNull(result1);
    }
}