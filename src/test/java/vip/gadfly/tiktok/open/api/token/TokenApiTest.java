package vip.gadfly.tiktok.open.api.token;
import vip.gadfly.tiktok.open.api.base.BaseTest;
import org.junit.Test;

public class TokenApiTest extends BaseTest {

    @Test
    public void get() {
        //用户回调 code
        String code = "wC6VDIrDDtA9rqI15LRD1F7Zdb7hPZM2MP2s";
        //获取 token
        TiktokOpenAccessTokenApi tokenApi =  new TiktokOpenAccessTokenApi();
        TiktokOpenAccessTokenResult result = tokenApi.get(code);
        isSuccess(result);
    }
}