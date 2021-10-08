package vip.gadfly.tiktok.open.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import vip.gadfly.tiktok.config.TtOpConfigStorage;
import vip.gadfly.tiktok.core.enums.TtOpTicketType;
import vip.gadfly.tiktok.core.util.URIUtil;
import vip.gadfly.tiktok.open.api.TtOpOAuth2Service;
import vip.gadfly.tiktok.open.bean.oauth2.TtOpAccessTokenRequest;
import vip.gadfly.tiktok.open.bean.oauth2.TtOpAccessTokenResult;
import vip.gadfly.tiktok.open.common.ITtOpBaseService;

import static vip.gadfly.tiktok.core.enums.TtOpApiUrl.OAuth2.*;

/**
 * @author Gadfly
 * @since 2021-09-18 14:19
 */
@Slf4j
@RequiredArgsConstructor
public class TtOpOauth2ServiceImpl implements TtOpOAuth2Service {
    private final ITtOpBaseService ttOpBaseService;

    protected TtOpConfigStorage getTtOpConfigStorage() {
        return this.ttOpBaseService.getTtOpConfigStorage();
    }

    @Override
    public String buildAuthorizationUrl(String redirectUri, String scope, String state, String optionalScope) {
        log.debug("构造oauth2授权的url连接，收到的参数：redirectUri={},scope={},state={}", redirectUri, scope, state);
        return String.format(CONNECT_OAUTH2_AUTHORIZE_URL.getUrl(getTtOpConfigStorage()),
                getTtOpConfigStorage().getClientKey(), URIUtil.encodeURIComponent(redirectUri), scope, StringUtils.trimToEmpty(state), StringUtils.trimToEmpty(optionalScope));
    }

    @Override
    public String buildSilentAuthorizationUrl(String redirectUri, String scope, String state) {
        log.debug("构造oauth2授权的url连接，收到的参数：redirectUri={},scope={},state={}", redirectUri, scope, state);
        return String.format(CONNECT_SILENT_OAUTH2_AUTHORIZE_URL.getUrl(null),
                getTtOpConfigStorage().getClientKey(), URIUtil.encodeURIComponent(redirectUri), scope, StringUtils.trimToEmpty(state));
    }

    @Override
    public TtOpAccessTokenResult getAccessTokenByAuthorizationCode(String authorizationCode) {
        log.debug("使用授权码换取用户信息的接口调用凭据，收到的参数：authorizationCode={}", authorizationCode);
        String url = OAUTH2_ACCESS_TOKEN_URL.getUrl(getTtOpConfigStorage());
        log.debug("url={}", url);
        TtOpAccessTokenRequest request = new TtOpAccessTokenRequest()
                .setGrantType(TtOpAccessTokenRequest.GRANT_TYPE_CODE)
                .setClientKey(getTtOpConfigStorage().getClientKey())
                .setClientSecret(getTtOpConfigStorage().getClientSecret())
                .setCode(authorizationCode);
        TtOpAccessTokenResult result = this.ttOpBaseService.post(url, request, TtOpAccessTokenResult.class);
        this.getTtOpConfigStorage().updateAccessToken(result);
        return result;
    }

    @Override
    public TtOpAccessTokenResult refreshAccessToken(String openid) {
        String url = OAUTH2_REFRESH_TOKEN_URL.getUrl(getTtOpConfigStorage());
        log.debug("url={}", url);
        TtOpAccessTokenRequest request = new TtOpAccessTokenRequest()
                .setGrantType(TtOpAccessTokenRequest.GRANT_TYPE_REFRESH)
                .setClientKey(getTtOpConfigStorage().getClientKey())
                .setClientSecret(getTtOpConfigStorage().getClientSecret());
        TtOpAccessTokenResult result = this.ttOpBaseService.post(url, request, TtOpAccessTokenResult.class);
        this.getTtOpConfigStorage().updateAccessToken(result);
        return result;
    }

    @Override
    public TtOpAccessTokenResult renewRefreshToken(String openid) {
        String url = OAUTH2_RENEW_REFRESH_TOKEN_URL.getUrl(getTtOpConfigStorage());
        log.debug("url={}", url);
        TtOpAccessTokenRequest request = new TtOpAccessTokenRequest()
                .setGrantType(null)
                .setClientKey(getTtOpConfigStorage().getClientKey())
                .setClientSecret(getTtOpConfigStorage().getClientSecret());
        TtOpAccessTokenResult result = this.ttOpBaseService.post(url, request, TtOpAccessTokenResult.class);
        this.getTtOpConfigStorage().updateAccessToken(result);
        return result;
    }

    @Override
    public String getClientToken(boolean forceRefresh) {
        if (!forceRefresh) {
            return ttOpBaseService.getTicket(TtOpTicketType.CLIENT);
        }
        String url = OAUTH2_CLIENT_TOKEN_URL.getUrl(getTtOpConfigStorage());
        log.debug("url={}", url);
        TtOpAccessTokenRequest request = new TtOpAccessTokenRequest()
                .setGrantType(TtOpAccessTokenRequest.GRANT_TYPE_CLIENT)
                .setClientKey(getTtOpConfigStorage().getClientKey())
                .setClientSecret(getTtOpConfigStorage().getClientSecret());
        TtOpAccessTokenResult result = this.ttOpBaseService.post(url, request, TtOpAccessTokenResult.class);
        this.getTtOpConfigStorage().updateTicket(TtOpTicketType.CLIENT, result.getAccessToken(), result.getExpiresIn());
        return result.getAccessToken();
    }

    @Override
    public String getJsapiTicket(boolean forceRefresh) {
        if (!forceRefresh) {
            return ttOpBaseService.getTicket(TtOpTicketType.JSAPI);
        }
        String rawUrl = OAUTH2_JSAPI_TICKET_URL.getUrl(getTtOpConfigStorage());
        String url = String.format(rawUrl, ttOpBaseService.getTicket(TtOpTicketType.CLIENT));
        log.debug("url={}", url);
        TtOpAccessTokenResult result = this.ttOpBaseService.get(url, TtOpAccessTokenResult.class);
        this.getTtOpConfigStorage().updateTicket(TtOpTicketType.JSAPI, result.getTicket(), result.getExpiresIn());
        return result.getTicket();
    }

    @Override
    public String getTicket(TtOpTicketType type, boolean forceRefresh) {
        switch (type) {
            case JSAPI:
                return this.getJsapiTicket(forceRefresh);
            default:
            case CLIENT:
                return this.getClientToken(forceRefresh);
        }
    }
}
