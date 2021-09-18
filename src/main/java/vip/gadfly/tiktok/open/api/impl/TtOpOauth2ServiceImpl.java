package vip.gadfly.tiktok.open.api.impl;

import lombok.RequiredArgsConstructor;
import vip.gadfly.tiktok.open.api.TtOpOAuth2Service;
import vip.gadfly.tiktok.open.base.ITiktokOpenBaseService;

/**
 * @author Gadfly
 * @since 2021-09-18 14:19
 */
@RequiredArgsConstructor
public class TtOpOauth2ServiceImpl implements TtOpOAuth2Service {
    private final ITiktokOpenBaseService tiktokOpenBaseService;
}
