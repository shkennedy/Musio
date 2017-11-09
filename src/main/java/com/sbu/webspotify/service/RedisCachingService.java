package com.sbu.webspotify.service;

import java.util.concurrent.TimeUnit;
import java.util.Date;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

// Docs https://docs.spring.io/spring-data/redis/docs/current/api/org/springframework/data/redis/core/RedisTemplate.html
// Example https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-redis
// Pom https://projects.spring.io/spring-data-redis/
// For our purposes Redis can be used as a key value store for recent requests, 
// Reduces DB and back-end load 
public class RedisCachingService {

	@Autowired
    private StringRedisTemplate template;

    private ValueOperations<String, String> stringStore;
    
    @PostConstruct
    private void initOperations() {
        stringStore = this.template.opsForValue();
    }
    
    /**
     * Returns Json String response for request
     */
    public String getCachedQueryResponse(String request) {
        return stringStore.get(request);
    }

    /**
     * Stores Json String response for request,
     * Sets timeout in seconds after which this entry will be deleted
     */
    public void setCachedQueryResponse(String request, String response, long timeoutSeconds) {    
        stringStore.set(request, response);
        template.expire(request, timeoutSeconds, TimeUnit.SECONDS);
    }

    /**
     * Stores Json String response for request,
     * Sets timeout date at which this entry will be deleted
     */
    public void setCachedQuertResponse(String request, String response, Date timeoutDate) {
        stringStore.set(request, response);
        template.expireAt(request, timeoutDate);
    }
}