package caffeineCache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.time.Duration;

/**
 * @author qian.pan on 2024/1/17.
 */
public class CaffeineCacheTest {

    Logger log = LoggerFactory.getLogger(CaffeineCacheTest.class);

    @Test
    public void HelloWorldTest() {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(Duration.ofMillis(100))
                .recordStats().build();

        String key = "demo";
        StopWatch stopWatch = new StopWatch();
        for (int i = 0; i < 20; i++) {
            stopWatch.start("loop" + i);
            Object value = cache.get(key, this::createIfNotExist);
            stopWatch.stop();
            log.info("cache value {}", value);
        }

        log.info(cache.stats().toString());
        log.info(stopWatch.prettyPrint());


    }

    private Object createIfNotExist(Object key) {
        log.info("createIfNotExist");
        return "createIfNotExist";
    }


}
