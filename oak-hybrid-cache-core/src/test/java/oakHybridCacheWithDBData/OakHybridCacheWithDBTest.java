package oakHybridCacheWithDBData;

import cn.xxywithpq.Application;
import jakarta.annotation.Resource;
import oakHybridCacheWithDBData.bean.UTaskProgressDO;
import oakHybridCacheWithDBData.mapper.UTaskProgressDOMapper;
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

    @Resource
    private UTaskProgressDOMapper uTaskProgressDOMapper;

    private void checkDataExistTest() {
        Long userId = 1L;
        int count = uTaskProgressDOMapper.selectByUserId(userId);
        if (count > 0) {
            log.warn("数据已经存在，无需初始化数据");
            return;
        }

        ArrayList<UTaskProgressDO> uTaskProgressDOS = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            UTaskProgressDO uTaskProgressDO = new UTaskProgressDO();
            uTaskProgressDO.setTaskCode("123");
            uTaskProgressDO.setUserId(userId++);
            uTaskProgressDO.setCreateTime(new Date());
            uTaskProgressDO.setVersion(0);
            uTaskProgressDO.setTaskCompleteStatus((byte) 1);
            uTaskProgressDO.setUpdateTime(new Date());
            uTaskProgressDO.setIsDeleted((byte) 0);
            uTaskProgressDOS.add(uTaskProgressDO);
        }

        count = uTaskProgressDOMapper.insertBatch(uTaskProgressDOS);
        log.info("insertBatch:{}", count);
    }

    @Test
    public void basicTest() {
        checkDataExistTest();
    }


}
