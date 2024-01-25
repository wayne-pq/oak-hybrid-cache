package cn.xxywithpq.application.cache;

import org.springframework.lang.Nullable;

/**
 * 统一对外接口
 *
 * @Author ：wayne
 */
public interface OakHybridCacheI {

    /**
     * 设置缓存数据
     *
     * @param key   键
     * @param value 值
     */
    void put(String key, @Nullable Object value);

    /**
     * 获取缓存数据
     *
     * @param key   键
     * @param value 值
     */
    <T> T get(String key, @Nullable Class<T> type);


}
