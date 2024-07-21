package com.miaoubich;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TokenBucketProperties.class)
public class TokenBucketConfiguration {

    @Bean
    TokenBucket tokenBucket(TokenBucketProperties properties) {
        return new TokenBucket(properties.getCapacity(), properties.getRefillRate(), properties.getUnit());
    }
}
