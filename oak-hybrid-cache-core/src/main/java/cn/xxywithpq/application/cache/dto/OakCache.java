package cn.xxywithpq.application.cache.dto;

import lombok.Data;

@Data
public class OakCache<T> {
    /**
     * 数据是否存在
     */
    protected Boolean exist = Boolean.FALSE;
    private T item;
    private Integer level;
    private Long version;
    private Boolean later = Boolean.FALSE;

    public static <T> OakCache<T> newInstance() {
        return new OakCache<>();
    }

    public OakCache<T> with(T item) {
        this.exist = Boolean.TRUE;
        this.item = item;
        return this;
    }

    public OakCache<T> with(T item, Integer level) {
        this.item = item;
        this.level = level;
        return this;
    }

    public OakCache<T> withVersion(Long version) {
        this.version = version;
        return this;
    }

    public OakCache<T> tryLater() {
        this.later = true;
        return this;
    }

    public Boolean isExist() {
        return this.exist;
    }
}
