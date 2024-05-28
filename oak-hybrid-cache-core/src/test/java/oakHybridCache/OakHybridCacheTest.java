package oakHybridCache;

import cn.xxywithpq.Application;
import cn.xxywithpq.application.cache.OakHybridCacheServiceI;
import cn.xxywithpq.application.cache.dto.OakCache;
import cn.xxywithpq.domian.cache.config.caffeine.CaffeineProperties;
import cn.xxywithpq.domian.cache.config.redis.RedisProperties;
import cn.xxywithpq.domian.cache.enums.CacheEnum;
import com.alibaba.cola.exception.BizException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


/**
 * @author qian.pan on 2024/1/17.
 */
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:application.yml")
public class OakHybridCacheTest {

    Logger log = LoggerFactory.getLogger(OakHybridCacheTest.class);

    @Resource
    private OakHybridCacheServiceI oakHybridCacheService;

    @Resource
    private CaffeineProperties caffeineProperties;
    @Resource
    private RedisProperties redisProperties;

    /**
     * 基础测试
     */
    @Test
    public void basicTest() throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(1);
        String key = "test";
        String value = "test";
//        AtomicReference<Integer> firstCacheLevel = new AtomicReference<>();
//        AtomicReference<String> firstCacheValue = new AtomicReference<>();
        oakHybridCacheService.put(key, value);
        OakCache<String> cacheValue = oakHybridCacheService.get(key, String.class);
//        new Thread(() -> {
//            while (true) {
//                OakCache<String> stringOakCache = oakHybridCacheService.get(key, String.class);
//                if (!stringOakCache.isEmpty()) {
//                    firstCacheLevel.set(stringOakCache.getLevel());
//                    firstCacheValue.set(stringOakCache.getItem());
//                    latch.countDown();
//                    break;
//                }
//            }
//        }).start();
//        latch.await();
//        OakCache<String> stringOakCache = oakHybridCacheService.get(key, String.class);
        Assertions.assertEquals(value, cacheValue.getItem());
//        Assertions.assertEquals(CacheEnum.CAFFEINE.getLevel(), firstCacheLevel.get());
        Thread.sleep(caffeineProperties.getExpireAfterWrite());
        OakCache<String> stringOakCache = oakHybridCacheService.get(key, String.class);
        //be sure after first cache timeout, the cache is redis level
        Assertions.assertEquals(CacheEnum.REDIS.getLevel(), stringOakCache.getLevel());
    }

    /**
     * key不能为空
     */
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

    /**
     * 过期功能测试
     */
    @Test
    public void expireTimeTest() throws InterruptedException {
        String key = "expireTimeTest";
        String value = "expireTimeTest";
        oakHybridCacheService.put(key, value);
        OakCache<String> stringOakCache = oakHybridCacheService.get(key, String.class);
        Assertions.assertEquals(value, stringOakCache.getItem());

        Thread.sleep(redisProperties.getExpireAfterWrite());
        stringOakCache = oakHybridCacheService.get(key, String.class);
        Assertions.assertNull(stringOakCache.getItem());
    }


}
