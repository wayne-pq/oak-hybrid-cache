package cn.xxywithpq.domian.cache.config.caffeine;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "oak.cache.caffeine")
@Data
public class CaffeineProperties {
    private Duration expireAfterWrite;
}