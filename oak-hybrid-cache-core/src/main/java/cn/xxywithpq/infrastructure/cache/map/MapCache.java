package cn.xxywithpq.infrastructure.cache.map;

import cn.xxywithpq.application.cache.dto.OakCache;
import cn.xxywithpq.domian.cache.AbstractCache;
import cn.xxywithpq.domian.cache.LocalCache;
import cn.xxywithpq.domian.cache.config.caffeine.CaffeineProperties;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static cn.xxywithpq.domian.cache.enums.CacheEnum.CAFFEINE;

/**
 * @author qian.pan on 2024/1/18.
 */
@Component
public class MapCache extends AbstractCache implements LocalCache, InitializingBean {

    @Resource
    private CaffeineProperties caffeineProperties;

    private Map<String, Object> mapCache;

    @Override
    public String getCode() {
        return CAFFEINE.getCode();
    }

    @Override
    public <T> OakCache<T> doGet(String key, Class<T> type) {
        Object value = mapCache.get(key);
        if (!Objects.isNull(value)) {
            OakCache<T> cache = JSONObject.parseObject(value.toString(), new TypeReference<>() {
            });
            cache.setLevel(CAFFEINE.getLevel());
            return cache;

//            return JSON.parseObject(JSON.toJSONString(value), OakCache.class);
//            if (type.isInstance(value)) {
//                return oakCache.with(type.cast(value), CAFFEINE.getLevel());
//            }
        }
        return null;
    }

    @Override
    public void doPut(String key, Object value) {
        mapCache.put(key, JSON.toJSONString(OakCache.newInstance().with(value)));
    }

    @Override
    public void afterPropertiesSet() {
        mapCache = new HashMap<>();
    }
}
