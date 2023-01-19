package com.nhnacademy.sessionproject.session;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.redis.core.RedisTemplate;


public class RedisSession implements Session{

    private final RedisTemplate<String, Object> redisTemplate;
    private final String sessionId;
    private final LocalDateTime creationTime;
    private LocalDateTime lastAccessTime;

    public RedisSession(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.sessionId = UUID.randomUUID().toString();
        this.creationTime = LocalDateTime.now();
        this.lastAccessTime = creationTime;
    }

    @Override
    public void updateAccessTime(){
        this.lastAccessTime = LocalDateTime.now();
    }

    @Override
    public String getSessionId() {
        return this.sessionId;
    }

    @Override
    public String getCreationTime() {
        return this.creationTime.toString();
    }

    @Override
    public String getLastAccessedTime() {
        return this.lastAccessTime.toString();
    }

    @Override
    public void setAttribute(String key, Object value){
        redisTemplate.opsForHash().put(this.sessionId, key,value.toString());
    }

    @Override
    public Object getAttribute(String key){
        return redisTemplate.opsForHash().get(this.sessionId, key);
    }

    @Override
    public void invalidate(){
        redisTemplate.opsForHash().delete(sessionId);
    }

    @Override
    public void removeAttribute(String key){
        redisTemplate.opsForHash().delete(this.sessionId, key);
    }
}
