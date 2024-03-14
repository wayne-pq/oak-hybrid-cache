//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.xxywithpq.domian.cache;

public interface Cache {
    String getCode();

    <T> T get(String key, Class<T> type);

    void put(String key, Object value);

}
