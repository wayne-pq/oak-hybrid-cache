package oakHybridCacheWithDBData.bean;

import java.util.Date;

/**
 * @author qian.pan on 2024/6/25.
 */

/**
 * 用户任务进度表
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getTaskCompleteStatus() {
        return taskCompleteStatus;
    }

    public void setTaskCompleteStatus(Byte taskCompleteStatus) {
        this.taskCompleteStatus = taskCompleteStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}