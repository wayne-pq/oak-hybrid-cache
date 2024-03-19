package cn.xxywithpq.application.cache;

/**
 * 统一对外接口
 *
 * @Author ：wayne
 */
public interface OakHybridCacheServiceI {

    /**
     * 设置缓存数据
     *
     * @param key   键
     * @param value 值
     */
    void put(String key, Object value);

    /**
     * 获取缓存数据
     *
     * @param key   键
     * @param value 值
     */
    <T> T get(String key, Class<T> type);


}
