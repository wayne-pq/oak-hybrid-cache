package oakHybridCache;

import cn.xxywithpq.Application;
import cn.xxywithpq.application.cache.OakHybridCacheServiceI;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author qian.pan on 2024/1/17.
 */
@SpringBootTest(classes = Application.class)
public class OakHybridCacheTest {

    Logger log = LoggerFactory.getLogger(OakHybridCacheTest.class);

    @Resource
    private OakHybridCacheServiceI oakHybridCacheService;


    @Test
    public void basicTest() {
        String key = "test";
        String value = "test";
        oakHybridCacheService.put(key, value);
        String cacheValue = oakHybridCacheService.get(key, String.class);
        Assertions.assertEquals(value, cacheValue);
    }


}
