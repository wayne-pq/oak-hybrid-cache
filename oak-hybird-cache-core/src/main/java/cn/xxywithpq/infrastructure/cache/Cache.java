//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.xxywithpq.infrastructure.cache;

import org.springframework.lang.Nullable;

public interface Cache {
    String getName();

    <T> T get(Object key, @Nullable Class<T> type);

    void put(Object key, @Nullable Object value);

}
