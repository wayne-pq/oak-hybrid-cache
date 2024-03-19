package oakHybridCache;

import cn.xxywithpq.Application;
import cn.xxywithpq.application.cache.OakHybridCacheServiceI;
import com.alibaba.cola.exception.BizException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static cn.xxywithpq.infrastructure.cache.redis.RedisCache.EXPIRE_TIME;

/**
 * @author qian.pan on 2024/1/17.
 */
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.yml")
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

    @Test
    public void keyBlankTest() {
        final String exceptionMsg = "key不能为空";
        BizException putException = Assertions.assertThrows(
                BizException.class,
                () -> {
                    String key = "";
                    String value = "test";
                    oakHybridCacheService.put(key, value);
                }
        );
        Assertions.assertEquals(exceptionMsg, putException.getMessage());

        BizException getException = Assertions.assertThrows(
                BizException.class,
                () -> {
                    String key = "";
                    oakHybridCacheService.get(key, String.class);
                }
        );
        Assertions.assertEquals(exceptionMsg, getException.getMessage());
    }

    @Test
    public void expireTimeTest() throws InterruptedException {
        String key = "expireTimeTest";
        String value = "expireTimeTest";
        oakHybridCacheService.put(key, value);
        String cacheValue = oakHybridCacheService.get(key, String.class);
        Assertions.assertEquals(value, cacheValue);

        Thread.sleep(Duration.of(EXPIRE_TIME, ChronoUnit.SECONDS));
        cacheValue = oakHybridCacheService.get(key, String.class);
        Assertions.assertNull(cacheValue);
    }


}
