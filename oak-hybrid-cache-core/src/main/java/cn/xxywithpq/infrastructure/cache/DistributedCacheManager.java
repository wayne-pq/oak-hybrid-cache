
package cn.xxywithpq.infrastructure.cache;

import cn.xxywithpq.domian.cache.DistributedCache;

/**
 * 缓存管理器接口
 */
public interface DistributedCacheManager extends CacheManager {
    /**
     * 获取指定类型的缓存
     *
     * @return 对应类型的缓存对象
     */
    DistributedCache getDistributedCache();

}
