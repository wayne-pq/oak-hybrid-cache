package cn.xxywithpq.application.cache;

import cn.xxywithpq.domian.cache.CacheDomainService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OakHybridCacheImpl implements OakHybridCacheI {

    @Resource
    private CacheDomainService cacheDomainService;

    @Override
    public void put(Object key, Object value) {
        cacheDomainService.put(key, value);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return cacheDomainService.get(key, type);
    }
}
