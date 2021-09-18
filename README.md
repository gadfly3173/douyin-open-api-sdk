# wptai-douyin-api 快速接入 几行代码实现抖音接入

开发中，暂不可直接使用

源项目：[https://gitee.com/hudan870614/wptai-douyin-api](https://gitee.com/hudan870614/wptai-douyin-api)

#### 架构
本SDK应当以**单例**的形式被调用。

整体架构与WxJava项目接近，提供了一个默认实现`DefaultTtOpServiceImpl.java`。其中的各api接口实现在各个子service中。

使用时需要修改子service的实现的话就去实现其对应的interface，通过`ITtOpBaseService.setXXXService(yourSubService)`来实现。
而如果需要修改`DefaultTtOpServiceImpl.java`中的实现，也就是覆盖`AbstractTtOpApiBase.java`的实现时，
请不要直接实现（implements） ITtOpBaseService，而是继承（extends） AbstractTtOpApiBase。

#### 项目介绍
   为抖音开发者提供快速接入方案、未依赖任何第三方mvc框架，支持所用java web 框架接入
   (抖音官方 java-sdk 太烂了，强列建议用这个！！！)
 

#### 安装教程

  **Maven引用**   

```
	<dependency>
		<groupId>vip.gadfly</groupId>
		<artifactId>douyin-open-api-sdk</artifactId>
		<version>1.0</version>
	</dependency>	
```

#### 规范说明

1："OAuth2.0" 相关：
```
appId : 抖音应用编码
appSecret :  抖音应用密钥
code : 用户授权码
accessToken : 权限访问今牌
redirectUri : 抖音扫一扫登陆后回调地址

```

2：类 相关：
```
API 类: 接口调用及处理类
Param 类: 接口入参实实体类
Result 类：接口出参实体类


若是抖音 /fans/data 接口，命名规则如下：

api接口类： FansDataApi
入参类： FansDataParam
结果集类： FansDataResult

其它相同

```

3：方法 相关：
```
//API接口请求时会带上 openId
withOpenId(openId) 

//API接口请求时会带上 accessToken
//注意：通过API获取 accessToken 后，会默认本地缓存，若不指定，后台会自动根据 openId 到缓存找 accessToken
withAccessToken(accessToken) 

//向API对应接口发起 get 请求
get()

//向API对应接口发起 post 请求 
post()

//向API对应接口发起 分页 请求 
page()

//向API对应接口发起抽样(默认为第一页20条) 
take()

```


  
#### 使用说明

** 加载配置文件及指定配置参数 ** 

1.在应用启动时AppConfig.getInstance()获取config实例，然后set值即可
以下配置仅供参考，推荐使用@PropertySource获取配置文件

```
# 抖音应用编码
dy_app_id=awnbafsadfsadf
# 抖音应用密钥
dy_app_secret=d1260sadfasdfasdfasdfasfsafssafas
# 接口编码
dy_app_charset=utf-8
# 接口请求时限
dy_app_timeout=3000
# 抖音URL
dy_host_url=https://open.douyin.com
# 扫一扫 code 回调地址
dy_redirect_url=http://www.aaaa.com/dy/callback

```

2. 加载及初始化(支持非配置文件方式)
使用jackson需要版本 >= 2.12

```
AppConfig config = new AppConfig();

//方式二: 指定各参数
config.setConfig(String appId, String appSecret, String appTimeout, String appCharset,String httpUrl,String redirectUri);

```

** API接口说明 ** 

1. 获取扫一扫路径

```
 OauthApi oauthApi = new OauthApi();
 String result = oauthApi.getScanUrl();

```

2. 获取 accessToken API

```
 //用户临时 code 
 String code = "wC6VDIrDDtA9rqI15LRD1F7Zdb7hPZM2MP2s";

 //获取 token
 AccessTokenApi tokenApi =  new AccessTokenApi();
 // accessToken 获取后会本地缓存,缓存key为appId
 AccessTokenResult result = tokenApi.get(code);
 
 //注：获取到 accessToken / openId 
 String accessToken = tokenResult.getAccessToken();
 String openId = tokenResult.getOpenId();

```

3. 获取 当前用户信息 API

```
 //accessToken 可通过 openId 本地获取
 String accessToken = "act.b37e3cda6f8199d6dc75a7d67d27d020ZLwgRsGmWmbRcQTmwNAoaEhGjqQh";
 String openId = "1b2242ab-08db-42f5-a9bb-947ac644676b";
 
 OauthUserInfoApi oauthUserInfoApi = new OauthUserInfoApi();
 OauthUserInfoResult result = oauthUserInfoApi
         .withAccessToken(accessToken) //withAccessToken()不调用则默认存缓存获取 accessToken
         .withOpenId(openId)
         .get();

```


#### 技术文档
  作者博客  [ 305786355 )  欢迎大家完善！
 
 