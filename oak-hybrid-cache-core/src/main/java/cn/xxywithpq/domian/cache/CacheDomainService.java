package cn.xxywithpq.domian.cache;

import cn.xxywithpq.application.cache.dto.OakCache;
import cn.xxywithpq.domian.cache.enums.CacheEnum;
import cn.xxywithpq.domian.gateway.CacheGateway;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qian.pan on 2023/12/22.
 */
@Component
public class CacheDomainService {
    @Resource
    private CacheGateway cacheGateway;

    private final Lock localCacheUpdatelock = new ReentrantLock();

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
    public <T> OakCache<T> get(String key, Class<T> type) {
        OakCache<T> cache = getLatestLocalCache(key, type);
        if (cache.isExist()) {
            return cache;
        }
        return getLatestDistributedCache(key, type);
    }

    private <T> OakCache<T> getLatestDistributedCache(String key, Class<T> type) {
        OakCache<T> oakCache = cacheGateway.getDistributedCache(CacheEnum.REDIS).get(key, type);
        //如果分布式缓存为空, 重新获取，为了防止缓存击穿，需要加分布式锁
        if (oakCache.isEmpty()) {
            //TODO  更新远程缓存
        }

        if (!oakCache.isEmpty()) {
            if (localCacheUpdatelock.tryLock()) {
                try {
                    cacheGateway.getLocalCache(CacheEnum.CAFFEINE).put(key, oakCache);
                } finally {
                    localCacheUpdatelock.unlock();
                }
            }
        }

        return oakCache;
    }

    private <T> OakCache<T> getLatestLocalCache(String key, Class<T> type) {
        return cacheGateway.getLocalCache(CacheEnum.CAFFEINE).get(key, type);
    }
}
