package cn.xxywithpq.domian.gateway;


import cn.xxywithpq.domian.cache.LocalCache;
import cn.xxywithpq.domian.cache.enums.CacheEnum;

/**
 * cache gateway
 *
 * @version 1.0
 */
public interface CacheGateway {
    LocalCache getLocalCache(CacheEnum cacheEnum);
}
