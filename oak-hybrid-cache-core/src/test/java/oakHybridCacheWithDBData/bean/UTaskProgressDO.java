package oakHybridCacheWithDBData.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author qian.pan on 2024/6/25.
 */

/**
 * 用户任务进度表
 */
@Data
public class UTaskProgressDO {
    /**
     * 主键
     */
    private String id;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 任务编码
     */
    private String taskCode;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 任务完成状态，1:完成中，2:已完成，默认0
     */
    private Byte taskCompleteStatus;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除,默认0,1:删除
     */
    private Byte isDeleted;

}