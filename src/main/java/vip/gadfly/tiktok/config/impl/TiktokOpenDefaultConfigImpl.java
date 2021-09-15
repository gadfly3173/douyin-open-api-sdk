package vip.gadfly.tiktok.config.impl;

import lombok.Data;
import vip.gadfly.tiktok.config.TiktokOpenConfigStorage;
import vip.gadfly.tiktok.config.TiktokOpenHostConfig;
import vip.gadfly.tiktok.core.enums.TicketType;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化.
 *
 * @author chanjarster
 */
@Data
public class TiktokOpenDefaultConfigImpl implements TiktokOpenConfigStorage, Serializable {
    private static final long serialVersionUID = -1L;

    protected volatile String appId;
    protected volatile String appSecret;

    protected volatile String oauth2redirectUri;

    protected volatile int retrySleepMillis = 1000;
    protected volatile int maxRetryTimes = 5;

    protected volatile String jsapiTicket;
    protected volatile long jsapiTicketExpiresTime;

    protected volatile String clientTicket;
    protected volatile long clientTicketExpiresTime;

    protected volatile Lock jsapiTicketLock = new ReentrantLock();
    protected volatile Lock clientTicketLock = new ReentrantLock();

    protected volatile File tmpDirFile;

    private TiktokOpenHostConfig hostConfig = null;

    @Override
    public String getTicket(TicketType type) {
        switch (type) {
            case CLIENT:
                return this.clientTicket;
            case JSAPI:
                return this.jsapiTicket;
            default:
                return null;
        }
    }

    public void setTicket(TicketType type, String ticket) {
        switch (type) {
            case CLIENT:
                this.clientTicket = ticket;
            case JSAPI:
                this.jsapiTicket = ticket;
                break;
            default:
        }
    }

    @Override
    public Lock getTicketLock(TicketType type) {
        switch (type) {
            case CLIENT:
                return this.clientTicketLock;
            case JSAPI:
                return this.jsapiTicketLock;
            default:
                return null;
        }
    }

    @Override
    public boolean isTicketExpired(TicketType type) {
        switch (type) {
            case CLIENT:
                return System.currentTimeMillis() > this.clientTicketExpiresTime;
            case JSAPI:
                return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
            default:
                return false;
        }
    }

    @Override
    public synchronized void updateTicket(TicketType type, String ticket, int expiresInSeconds) {
        switch (type) {
            case JSAPI:
                this.jsapiTicket = ticket;
                // 预留200秒的时间
                this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
                break;
            case CLIENT:
                this.clientTicket = ticket;
                // 预留200秒的时间
                this.clientTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
                break;
            default:
        }
    }

    @Override
    public void expireTicket(TicketType type) {
        switch (type) {
            case JSAPI:
                this.jsapiTicketExpiresTime = 0;
                break;
            case CLIENT:
                this.clientTicketExpiresTime = 0;
                break;
            default:
        }
    }

    @Override
    public boolean autoRefreshToken() {
        return true;
    }

    @Override
    public TiktokOpenHostConfig getHostConfig() {
        return this.hostConfig;
    }

    @Override
    public void setHostConfig(TiktokOpenHostConfig hostConfig) {
        this.hostConfig = hostConfig;
    }

}
