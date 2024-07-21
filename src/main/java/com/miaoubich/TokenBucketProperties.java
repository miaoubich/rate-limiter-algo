package com.miaoubich;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "tokenbucket")
public class TokenBucketProperties {

	private long capacity;
    private long refillRate;
    private TimeUnit unit;
}
