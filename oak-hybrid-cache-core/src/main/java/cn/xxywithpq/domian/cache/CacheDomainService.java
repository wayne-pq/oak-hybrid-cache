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
        synchronized (this) {
            cacheGateway.getLocalCache(CacheEnum.CAFFEINE).put(key, value);
            cacheGateway.getDistributedCache(CacheEnum.REDIS).put(key, value);
        }
    }

    /**
     * 获取缓存:
     * 优先读取本地缓存，本地缓存无效或不存在时再读取远程缓存；
     * 更新本地缓存或远程缓存时，同一时刻仅允许一个线程操作。
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> type) {
        T t = getLatestLocalCache(key, type);
        if (t != null) {
            return t;
        }
        return getLatestDistributedCache(key, type);
    }

    private <T> T getLatestDistributedCache(String key, Class<T> type) {
        return cacheGateway.getDistributedCache(CacheEnum.REDIS).get(key, type);
    }

    private <T> T getLatestLocalCache(String key, Class<T> type) {
        return cacheGateway.getDistributedCache(CacheEnum.REDIS).get(key, type);
    }
}
