package oakHybridCacheWithDBData;

import cn.xxywithpq.Application;
import jakarta.annotation.Resource;
import oakHybridCacheWithDBData.bean.UTaskDetailDO;
import oakHybridCacheWithDBData.bean.UTaskProgressDO;
import oakHybridCacheWithDBData.dto.UTaskProgressDetailDTO;
import oakHybridCacheWithDBData.mapper.UTaskDetailDOMapper;
import oakHybridCacheWithDBData.mapper.UTaskProgressDOMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author qian.pan on 2024/1/17.
 */
@SpringBootTest(classes = Application.class)
@MapperScan(basePackages = {"oakHybridCacheWithDBData.mapper"})
@ActiveProfiles("withdb")
public class OakHybridCacheWithDBTest {

    Logger log = LoggerFactory.getLogger(OakHybridCacheWithDBTest.class);

    private final String TASK_CODE = "taskDemo";
    private final Long USER_ID = 1L;
    @Resource
    private UTaskProgressDOMapper taskProgressDOMapper;
    @Resource
    private UTaskDetailDOMapper taskDetailDOMapper;

    private void checkTaskProgressDataExistTest() {
        int count = taskProgressDOMapper.selectByUserId(USER_ID);
        if (count > 0) {
            log.warn("数据已经存在，无需初始化数据");
            return;
        }

        ArrayList<UTaskProgressDO> uTaskProgressDOS = new ArrayList<>();

        long startUserId = USER_ID;
        for (int i = 0; i < 100; i++) {
            UTaskProgressDO uTaskProgressDO = new UTaskProgressDO();
            uTaskProgressDO.setTaskCode(TASK_CODE);
            uTaskProgressDO.setUserId(startUserId++);
            uTaskProgressDO.setCreateTime(new Date());
            uTaskProgressDO.setVersion(0);
            uTaskProgressDO.setTaskCompleteStatus((byte) 1);
            uTaskProgressDO.setUpdateTime(new Date());
            uTaskProgressDO.setIsDeleted((byte) 0);
            uTaskProgressDOS.add(uTaskProgressDO);
        }

        count = taskProgressDOMapper.insertBatch(uTaskProgressDOS);
        log.info("insertBatch:{}", count);
    }

    private void checkTaskDetailDataExistTest() {
        int count = taskDetailDOMapper.selectByTaskCode(TASK_CODE);
        if (count > 0) {
            log.warn("数据已经存在，无需初始化数据");
            return;
        }

        UTaskDetailDO uTaskDetailDO = new UTaskDetailDO();
        uTaskDetailDO.setTaskCode(TASK_CODE);
        uTaskDetailDO.setTaskName(TASK_CODE);
        uTaskDetailDO.setCreateTime(new Date());
        uTaskDetailDO.setVersion(0);
        uTaskDetailDO.setUpdateTime(new Date());
        uTaskDetailDO.setIsDeleted((byte) 0);

        taskDetailDOMapper.insert(uTaskDetailDO);
    }

    @Test
    public void basicTest() {
        checkTaskProgressDataExistTest();
        checkTaskDetailDataExistTest();

        UTaskProgressDetailDTO uTaskProgressDetailDTO = taskProgressDOMapper.selectDetailByUserId(USER_ID);
        log.info("uTaskProgressDetailDTO:{}", uTaskProgressDetailDTO);
        Assertions.assertNotNull(uTaskProgressDetailDTO);
    }


}
