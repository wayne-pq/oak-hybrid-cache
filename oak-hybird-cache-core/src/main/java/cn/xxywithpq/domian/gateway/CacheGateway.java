package cn.xxywithpq.domian.gateway;


import cn.xxywithpq.infrastructure.cache.Cache;

/**
 * cache gateway
 *
 * @version 1.0
 */
public interface CacheGateway {
    Cache getCache(String name);
}
