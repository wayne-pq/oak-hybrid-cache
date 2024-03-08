package caffeineCache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.time.Duration;
import java.util.ArrayList;

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

    @Test
    public void softValuesTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.info("Thread start.");
            Cache<String, Object> cache = Caffeine.newBuilder()
                    .softValues()
                    .expireAfterWrite(Duration.ofMillis(60000))
                    .build();

            try {
                int i = 0;
                while (!Thread.interrupted()) {
                    ArrayList<Object> objects = new ArrayList<>();
                    objects.add(i);
                    if (cache.estimatedSize() < 1000) {
                        System.out.println("size" + cache.estimatedSize());
                    }
                    cache.put(String.valueOf(i), objects);
                    i++;
                }
            } finally {
                log.info("Thread stopped.");
            }
        });
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }

    private Object createIfNotExist(Object key) {
        log.info("createIfNotExist");
        return "createIfNotExist";
    }


}
