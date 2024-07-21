package com.miaoubich;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/*
 * In this controller, the myEndpoint method tries to consume a token for each request. 
 * If there are no tokens left, it returns a “Too many requests” response. This is a 
 * simple way to implement rate limiting in a Spring Boot application. Remember to call 
 * the shutdown method of TokenBucket when your application is shutting down to stop the 
 * token refill task. You can do this in a @PreDestroy method in one of your @Service or 
 * @Component classes.
 * Please note that this is a very basic implementation and might not be suitable for a 
 * production environment. For a production environment, consider using a more robust 
 * solution like Google’s Guava RateLimiter or a distributed rate limiter if your application 
 * is running on multiple instances. Also, this code does not handle the case where the refill 
 * task execution fails for some reason. You might want to add error handling code depending 
 * on your requirements.
 * This code is provided as an example and should be tested thoroughly before use. It is always 
 * recommended to understand the code thoroughly before using it in your application.
 * */

@RestController
@RequiredArgsConstructor
public class MyController {

    private final TokenBucket tokenBucket;

    @GetMapping("/my-endpoint")
    public String myEndpoint() {
        if (tokenBucket.tryConsume()) {
            return "Request processed";
        } else {
            return "Too many requests";
        }
    }
}
