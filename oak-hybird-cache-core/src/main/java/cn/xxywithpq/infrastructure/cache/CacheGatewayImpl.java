package cn.xxywithpq.infrastructure.cache;


import cn.xxywithpq.domian.cache.LocalCache;
import cn.xxywithpq.domian.cache.enums.CacheEnum;
import cn.xxywithpq.domian.gateway.CacheGateway;
import cn.xxywithpq.infrastructure.cache.caffeine.CaffeineCacheManager;
import jakarta.annotation.Resource;

/**
 * cache gateway
 *
 * @version 1.0
 */
public class CacheGatewayImpl implements CacheGateway {

    @Resource
    private CaffeineCacheManager caffeineCacheManager;

    @Override
    public LocalCache getLocalCache(CacheEnum cacheEnum) {
        if (cacheEnum.equals(CacheEnum.CAFFEINE)) {
            return caffeineCacheManager.getLocalCache();
        }
        return null;
    }
}
