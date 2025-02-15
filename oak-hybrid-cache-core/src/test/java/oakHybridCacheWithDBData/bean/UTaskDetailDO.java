package oakHybridCacheWithDBData.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author qian.pan on 2024/7/4.
 */

/**
 * 用户成长值任务详情表
 */
@Data
public class UTaskDetailDO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 任务编码
     */
    private String taskCode;

    /**
     * 任务名称
     */
    private String taskName;

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
     * 是否删除，默认0，1:是
     */
    private Byte isDeleted;

    private Long userId;

}