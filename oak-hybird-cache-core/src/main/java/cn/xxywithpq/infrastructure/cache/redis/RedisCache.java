package cn.xxywithpq.infrastructure.cache.redis;

import cn.xxywithpq.domian.cache.LocalCache;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import static cn.xxywithpq.domian.cache.enums.CacheEnum.REDIS;

/**
 * @author qian.pan on 2024/1/18.
 */
@Component
public class RedisCache implements LocalCache {
    @Resource
    private RedisOperations<String, String> operations;

    @Override
    public String getCode() {
        return REDIS.getCode();
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        String value = operations.opsForValue().get(key);
        if (value != null) {
            return JSON.parseObject(value, type);
        }
        return null;
    }

    @Override
    public void put(String key, Object value) {
        operations.opsForValue().set(key, JSON.toJSONString(value));
    }
}
