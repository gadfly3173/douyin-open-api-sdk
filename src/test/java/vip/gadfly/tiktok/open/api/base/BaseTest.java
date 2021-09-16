package vip.gadfly.tiktok.open.api.base;

import org.junit.*;
import vip.gadfly.tiktok.config.AppConfig;
import vip.gadfly.tiktok.core.utils.JsonUtil;
import vip.gadfly.tiktok.open.base.TiktokOpenBaseResult;

public class BaseTest {
    static AppConfig config = new AppConfig();

    public void isSuccess(Object result){
        System.out.println(JsonUtil.objectToJson(result));
        if(result instanceof TiktokOpenBaseResult){
            Assert.assertEquals(((TiktokOpenBaseResult)result).getErrCode(),0);
        }
    }


}
