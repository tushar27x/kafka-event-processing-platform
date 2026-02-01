package com.tushar27x.consumer.services;

import com.tushar27x.consumer.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class RedisAggregationService {

    private final StringRedisTemplate redis;

    public void aggregate(EventDTO event) {

        redis.opsForValue().increment("events:total");
        redis.opsForValue().increment("events:type:" + event.getEventType());
        redis.opsForValue().increment("events:user:" + event.getUserId());
        redis.opsForValue().increment("events:user:" + event.getUserId()
            + ":type:" + event.getEventType());

        LocalDate date = Instant.ofEpochMilli(event.getTimestamp())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        redis.opsForValue().increment("event:date:" + date);

    }
}
