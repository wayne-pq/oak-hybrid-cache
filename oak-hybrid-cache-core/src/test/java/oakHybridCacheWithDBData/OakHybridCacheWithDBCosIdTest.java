package oakHybridCacheWithDBData;

import cn.hutool.core.util.IdUtil;
import cn.xxywithpq.Application;
import me.ahoo.cosid.snowflake.SnowflakeId;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


/**
 * @author qian.pan on 2024/1/17.
 */
@SpringBootTest(classes = Application.class)
@MapperScan(basePackages = {"oakHybridCacheWithDBData.mapper"})
@ActiveProfiles("withdb")
public class OakHybridCacheWithDBCosIdTest {

    Logger log = LoggerFactory.getLogger(OakHybridCacheWithDBCosIdTest.class);
    @Qualifier("__share__SnowflakeId")
    @Autowired
    private SnowflakeId snowflakeId;

    @Test
    public void CosIdBasicTest() {
        for (int i = 0; i < 100; i++) {
            log.info("snowflakeId:{}", snowflakeId.generate());
        }
    }

    @Test
    public void hutoolSnowflakeMod() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            long id = IdUtil.getSnowflake(1).nextId();
            Thread.sleep(1);
            log.info("id: {}, after mod 4: {}", id, id % 4);
        }
    }

    @Test
    public void cosIdSnowflakeMod() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            long id = snowflakeId.generate();
            Thread.sleep(1);
            List<String> files = new ArrayList<>();
            log.info("id: {}, after mod 4: {}", id, id % 4);
        }
    }
}
