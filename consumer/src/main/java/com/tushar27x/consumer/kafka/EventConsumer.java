package com.tushar27x.consumer.kafka;

import com.tushar27x.consumer.dto.EventDTO;
import com.tushar27x.consumer.metrics.EventMetrics;
import com.tushar27x.consumer.services.AggregationService;
import com.tushar27x.consumer.services.IdempotencyService;
import com.tushar27x.consumer.services.RedisAggregationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final AggregationService aggregationService;
    private final RedisAggregationService redisAggregationService;
    private final IdempotencyService idempotencyService;
    private final EventMetrics eventMetrics;

    @KafkaListener(
            topics = "user.activity.events",
            groupId = "user-activity-consumer"
    )
    public void consumer(EventDTO event) {

        try{
            if ("FAIL".equals(event.getEventType())) {
                throw new RuntimeException("Simulated failure");
            }

            boolean isNew = idempotencyService
                    .markIfNotProcessed(event.getEventId());
            if (!isNew) {
                log.warn("Duplicate event skipped: {}", event.getEventId());
                eventMetrics.duplicate();
                return;
            }

            redisAggregationService.aggregate(event);
            aggregationService.aggregate(event);

            eventMetrics.processed();
            log.info("Processing event: {}", event);
        } catch (RuntimeException ex) {
            log.error("Event processing failed: {}", event, ex);
            throw ex;
        }

    }
}
