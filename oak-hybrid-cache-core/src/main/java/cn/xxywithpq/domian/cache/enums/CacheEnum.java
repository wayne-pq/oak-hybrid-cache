package cn.xxywithpq.domian.cache.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qian.pan on 2024/1/18.
 */
@Getter
@AllArgsConstructor
public enum CacheEnum {
    CAFFEINE("caffeine", 1),

    REDIS("redis", 2);


    private final String code;
    private final Integer level;

}
