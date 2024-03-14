package cn.xxywithpq.infrastructure.cache.caffeine;

import cn.xxywithpq.domian.cache.AbstractCache;
import cn.xxywithpq.domian.cache.LocalCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.time.Duration;

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
    public <T> T doGet(String key, Class<T> type) {
        Object object = caffeineCache.getIfPresent(key);
        if (object != null) {
            return (T) object;
        }
        return null;
    }

    @Override
    public void doPut(String key, Object value) {
        caffeineCache.put(key, value);
    }
}
