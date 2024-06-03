package cn.xxywithpq.domian.cache;

import cn.xxywithpq.application.cache.dto.OakCache;
import cn.xxywithpq.domian.cache.enums.CacheEnum;
import cn.xxywithpq.domian.gateway.CacheGateway;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * @author qian.pan on 2023/12/22.
 */
@Component
@Slf4j
public class CacheDomainService {
    @Resource
    private CacheGateway cacheGateway;

    private final Lock localCacheUpdatelock = new ReentrantLock();

    @Resource
    private RedissonClient distributedCacheUpdateLock;

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
        if (!Objects.isNull(cache)) {
            return cache;
        }
        return getLatestDistributedCache(key, type, null);
    }

    private <T> OakCache<T> getLatestDistributedCache(String key, Class<T> type, Function<String, T> getOriginalValue) {
        OakCache<T> oakCache = cacheGateway.getDistributedCache(CacheEnum.REDIS).get(key, type);
        //如果分布式缓存为空, 重新获取，为了防止缓存击穿，需要加分布式锁
        if (Objects.isNull(oakCache)) {
            if (Objects.isNull(getOriginalValue)) {
                log.debug("none of function left");
                oakCache = OakCache.newInstance();
                cacheGateway.getDistributedCache(CacheEnum.REDIS).put(key, oakCache);
            } else {
                //            RLock lock = null;
//            distributedCacheUpdateLock.getLock()
                // TODO 拿原始数据的逻辑
            }
        }

        //同步本地缓存
        if (!Objects.isNull(oakCache)) {
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

    public <T> OakCache<T> tryToUpdateCacheByLock(String key, Class<T> type) {
        return null;
    }
}
