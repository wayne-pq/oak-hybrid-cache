package cn.xxywithpq.infrastructure.cache.redis;

import cn.xxywithpq.domian.cache.AbstractCache;
import cn.xxywithpq.domian.cache.DistributedCache;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static cn.xxywithpq.domian.cache.enums.CacheEnum.REDIS;

/**
 * @author qian.pan on 2024/1/18.
 */
@Component
public class RedisCache extends AbstractCache implements DistributedCache {
    public static final Long EXPIRE_TIME = 10L;
    @Resource
    private RedisOperations<String, String> operations;

    @Override
    public String getCode() {
        return REDIS.getCode();
    }

    @Override
    public <T> T doGet(String key, Class<T> type) {
        String value = operations.opsForValue().get(key);
        if (value != null) {
            return JSON.parseObject(value, type);
        }
        return null;
    }

    @Override
    public void doPut(String key, Object value) {
        operations.opsForValue().set(key, JSON.toJSONString(value), EXPIRE_TIME, TimeUnit.SECONDS);
    }
}
