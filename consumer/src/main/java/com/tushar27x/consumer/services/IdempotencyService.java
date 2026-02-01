package com.tushar27x.consumer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class IdempotencyService {
    private static final Duration TTL = Duration.ofHours(24);

    private final StringRedisTemplate redis;

    public boolean markIfNotProcessed(String eventId) {
        String key = "processed:event:" + eventId;

        Boolean success = redis.opsForValue()
                .setIfAbsent(key, "1", TTL);

        return Boolean.TRUE.equals(success);

    }

}
