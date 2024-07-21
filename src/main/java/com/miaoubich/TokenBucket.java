package com.miaoubich;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

/*
 * In this example, the TokenBucket service is initialized with a capacity and a refill rate. 
 * The tryConsume method is used to consume tokens from the bucket. If there are no tokens left, 
 * it returns false. The shutdown method is used to stop the token refill task when it’s no longer needed.
 * You can use this service in your Spring Boot controllers or other services by autowiring it:
 * 
 * */

@Service
public class TokenBucket {

    private final long capacity;
    private long tokens;
    private final ScheduledExecutorService executor;

    public TokenBucket(long capacity, long refillRate, TimeUnit unit) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.executor = Executors.newSingleThreadScheduledExecutor();

        Runnable refillTask = () -> {
            if (tokens < capacity) {
                tokens++;
            }
        };

        long period = unit.toMillis(1) / refillRate;
        executor.scheduleAtFixedRate(refillTask, period, period, TimeUnit.MILLISECONDS);
    }

    public synchronized boolean tryConsume() {
        if (tokens > 0) {
            tokens--;
            return true;
        } else {
            return false;
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
