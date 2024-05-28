package cn.xxywithpq.domian.cache.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "oak.cache.redis")
@Data
public class RedisProperties {
    private Duration expireAfterWrite;
}