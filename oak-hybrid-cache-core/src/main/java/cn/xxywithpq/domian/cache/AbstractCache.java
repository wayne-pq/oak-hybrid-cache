//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.xxywithpq.domian.cache;

import com.alibaba.cola.exception.ExceptionFactory;
import org.springframework.util.StringUtils;

import static cn.xxywithpq.domian.cache.exception.BizException.KEY_BLANK;

public abstract class AbstractCache implements Cache {

    private final String PREFIX = "oakHybridCache:";

    protected abstract <T> T doGet(String key, Class<T> type);

    protected abstract void doPut(String key, Object value);

    @Override
    public <T> T get(String key, Class<T> type) {
        keyNonNull(key);
        key = buildPrefix(key);
        return doGet(key, type);
    }

    @Override
    public void put(String key, Object value) {
        keyNonNull(key);
        key = buildPrefix(key);
        doPut(key, value);
    }


    private void keyNonNull(String key) {
        if (!StringUtils.hasText(key)) {
            throw ExceptionFactory.bizException(KEY_BLANK.getCode(), KEY_BLANK.getMessage());
        }
    }


    /**
     * 增加key前缀
     *
     * @return
     */
    private String buildPrefix(String key) {
        return PREFIX.concat(key);
    }
}
