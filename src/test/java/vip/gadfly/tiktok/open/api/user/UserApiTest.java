package vip.gadfly.tiktok.open.api.user;

import vip.gadfly.tiktok.open.api.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import vip.gadfly.tiktok.open.api.oauth.OauthApi;
import vip.gadfly.tiktok.open.api.token.AccessTokenApi;
import vip.gadfly.tiktok.open.api.token.AccessTokenResult;
import vip.gadfly.tiktok.open.api.video.VideoListApi;
import vip.gadfly.tiktok.open.api.video.VideoListResult;

public class UserApiTest extends BaseTest {

    @Test
    public void getOauthConnectUrl() {
        OauthApi oauthApi = new OauthApi();
        String result = oauthApi.getScanUrl();
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void tokenApi() {
        String code = "wUtj1dPSMLqIoy5UtL9Tx6O4Yk1hfTLgdqnY";
        AccessTokenApi tokenApi =  new AccessTokenApi();
        AccessTokenResult tokenResult = tokenApi.get(code);
        Assert.assertNotNull(tokenResult);
    }

    @Test
    public void userApiTest() {



        String accessToken = "act.a2508293c6bb4a3779e8025f5d757050O1zn3q7KGOJbLHsUWbZBuZ79Uk6G";
        String openId = "1b2242ab-08db-42f5-a9bb-947ac644676b";

//        accessToken = tokenResult.getAccessToken();
//        openId = tokenResult.getOpenId();

//        OauthUserInfoApi oauthUserInfoApi = new OauthUserInfoApi();
//        OauthUserInfoResult result = oauthUserInfoApi
//                .withAccessToken(accessToken)
//                .withOpenId(openId)
//                .get();
//        isSuccess(result);

//        FollowingListApi followingListApi = new FollowingListApi();
//        FollowingListResult followingListResult = followingListApi
//                .withAccessToken(accessToken)
//                .withOpenId(openId).page(0,1);
//        isSuccess(followingListResult);
//        FansListApi fansListApi = new FansListApi();
//        FansListResult fansListResult = fansListApi
//                .withAccessToken(accessToken)
//                .withOpenId(openId).page(1553940121,20);
//        isSuccess(fansListResult);

        VideoListApi videoListApi = new VideoListApi();
        VideoListResult result1 = videoListApi
                .withAccessToken(accessToken)
                .withOpenId(openId)
                .page(1563989822000L, 20L);
        isSuccess(result1);

//        DataExternalUserParam param = new DataExternalUserParam();
//        param.setDataType(DataExternalUserEnum.FANS);
//        DataExternalUserApi dataExternalUserApi = new DataExternalUserApi();
//        DataExternalUserResult result2 = dataExternalUserApi.withAccessToken(accessToken)
//                .withOpenId(openId)
//                .get(param);
//        isSuccess(result2);

//        FansDataApi fansDataApi = new FansDataApi();
//        FansDataResult result = fansDataApi.withOpenId(openId).withAccessToken(accessToken).get();
//        isSuccess(result);
//
//        FansListApi fansListApi = new FansListApi();
//        FansListResult fansListResult = fansListApi.withOpenId(openId).withAccessToken(accessToken).page(0,1);
//        isSuccess(fansListResult);
//
//        HotsearchSentencesApi hotsearchSentencesApi = new HotsearchSentencesApi();
//        HotsearchSentencesResult result =  hotsearchSentencesApi.get();
//        isSuccess(result);

//        HotsearchTrendingSentencesApi hotsearchTrendingSentencesApi = new HotsearchTrendingSentencesApi();
//        HotsearchSentencesResult hotsearchSentencesResult = hotsearchTrendingSentencesApi.take();
//        isSuccess(hotsearchSentencesResult);


//        StarHostListApi starHostListApi = new StarHostListApi();
//        StarHostListResult starHostListResult = starHostListApi.get();
//        isSuccess(starHostListResult);


//        DiscoveryEntRankItemApi discoveryEntRankItemApi = new DiscoveryEntRankItemApi();
//        DiscoveryEntRankItemResult discoveryEntRankItemResult = discoveryEntRankItemApi.get();
//        isSuccess(discoveryEntRankItemResult);



    }
}