package cn.xxywithpq.domian.cache;

import org.springframework.stereotype.Component;

/**
 * @author qian.pan on 2023/12/22.
 */
@Component
public class CacheDomainService {
    public void put(Object key, Object value) {

    }

    public <T> T get(Object key, Class<T> type) {
        return null;
    }

}
