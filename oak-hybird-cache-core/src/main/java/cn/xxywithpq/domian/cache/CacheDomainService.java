package cn.xxywithpq.domian.cache;

import cn.xxywithpq.domian.cache.enums.CacheEnum;
import cn.xxywithpq.domian.gateway.CacheGateway;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author qian.pan on 2023/12/22.
 */
@Component
public class CacheDomainService {
    @Resource
    private CacheGateway cacheGateway;

    public void put(String key, Object value) {
        cacheGateway.getLocalCache(CacheEnum.CAFFEINE).put(key, value);
        cacheGateway.getDistributedCache(CacheEnum.REDIS).put(key, value);
    }

    public <T> T get(String key, Class<T> type) {
        T t = cacheGateway.getLocalCache(CacheEnum.CAFFEINE).get(key, type);
        if (t == null) {
            t = cacheGateway.getDistributedCache(CacheEnum.REDIS).get(key, type);
        }
        return t;
    }

}
