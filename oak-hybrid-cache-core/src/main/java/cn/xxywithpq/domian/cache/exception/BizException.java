package cn.xxywithpq.domian.cache.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务异常code
 *
 * @author zk
 * @date 2022/9/8 14:38
 */
@AllArgsConstructor
@Getter
public enum BizException {
    /**
     * 通用报错code以IAW开头，后续接 xxx 数字
     */
    KEY_BLANK("biz_001", "key不能为空"),
    ;

    private final String code;
    private final String message;


}
