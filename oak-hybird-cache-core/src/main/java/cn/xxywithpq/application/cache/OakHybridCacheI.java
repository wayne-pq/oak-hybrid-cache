package cn.xxywithpq.application.cache;

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
    void set(String key, Object value);

    /**
     * 获取缓存数据
     *
     * @param key   键
     * @param value 值
     */
    void get(String key, Object value);
}
