package cn.xxywithpq.infrastructure.cache.caffeine;

import cn.xxywithpq.application.cache.dto.OakCache;
import cn.xxywithpq.domian.cache.AbstractCache;
import cn.xxywithpq.domian.cache.LocalCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

import static cn.xxywithpq.domian.cache.enums.CacheEnum.CAFFEINE;

/**
 * @author qian.pan on 2024/1/18.
 */
@Component
public class CaffeineCache extends AbstractCache implements LocalCache {
    private final com.github.benmanes.caffeine.cache.Cache<String, Object> caffeineCache = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(Duration.ofMillis(1000))
            .recordStats().build();

    @Override
    public String getCode() {
        return CAFFEINE.getCode();
    }

    @Override
    public <T> OakCache<T> doGet(String key, Class<T> type) {
        OakCache<T> oakCache = OakCache.newInstance();
        Object value = caffeineCache.getIfPresent(key);
        if (!Objects.isNull(value)) {
            if (type.isInstance(value)) {
                return oakCache.with(type.cast(value));
            }
        }
        return oakCache;
    }

    @Override
    public void doPut(String key, Object value) {
        caffeineCache.put(key, value);
    }
}
