package com.tushar27x.consumer.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class EventMetrics {

    private final Counter processed;
    private final Counter failed;
    private final Counter duplicate;
    private final Counter retried;
    private final Counter dlq;

    public EventMetrics(MeterRegistry registry) {
        this.processed = Counter.builder("events_processed_total")
                .description("Total number of successful events processed")
                .register(registry);

        this.failed = Counter.builder("events_failed_total")
                .description("Total number of events failed")
                .register(registry);

        this.duplicate = Counter.builder("events_duplicate_total")
                .description("Total number of duplicate events")
                .register(registry);

        this.retried = Counter.builder("events_retried_total")
                .description("Total number of events retried")
                .register(registry);

        this.dlq = Counter.builder("events_dlq_total")
                .description("Total number of events added to DLQ")
                .register(registry);
    }

    public void processed() {
        processed.increment();
    }

    public void failed() {
        failed.increment();
    }

    public void duplicate() {
        duplicate.increment();
    }

    public void retried() {
        retried.increment();
    }
    public void dlq() {
        dlq.increment();
    }
}
