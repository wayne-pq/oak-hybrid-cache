package oakHybridCacheWithDBData.dto;

import lombok.Data;

/**
 * @author qian.pan on 2024/6/25.
 */

/**
 * 用户任务进度表
 */
@Data
public class UTaskProgressDetailDTO {


    /**
     * 任务编码
     */
    private String taskCode;
    private String taskName;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 任务完成状态，1:完成中，2:已完成，默认0
     */
    private Byte taskCompleteStatus;


}