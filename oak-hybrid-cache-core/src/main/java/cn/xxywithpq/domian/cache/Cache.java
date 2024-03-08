//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.xxywithpq.domian.cache;

import org.springframework.lang.Nullable;

public interface Cache {
    String getCode();

    <T> T get(String key, @Nullable Class<T> type);

    void put(String key, @Nullable Object value);

}
