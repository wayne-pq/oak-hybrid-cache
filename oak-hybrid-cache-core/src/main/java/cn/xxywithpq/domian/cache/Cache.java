//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.xxywithpq.domian.cache;

import cn.xxywithpq.application.cache.dto.OakCache;

public interface Cache {
    String getCode();

    <T> OakCache<T> get(String key, Class<T> type);

    void put(String key, Object value);

}
