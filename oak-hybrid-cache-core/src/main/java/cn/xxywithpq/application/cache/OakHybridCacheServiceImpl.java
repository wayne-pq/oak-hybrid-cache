package cn.xxywithpq.application.cache;

import cn.xxywithpq.application.cache.dto.OakCache;
import cn.xxywithpq.domian.cache.CacheDomainService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OakHybridCacheServiceImpl implements OakHybridCacheServiceI {

    @Resource
    private CacheDomainService cacheDomainService;

    @Override
    public void put(String key, Object value) {
        cacheDomainService.put(key, value);
    }

    @Override
    public <T> OakCache<T> get(String key, Class<T> type) {
        return cacheDomainService.get(key, type);
    }
}
