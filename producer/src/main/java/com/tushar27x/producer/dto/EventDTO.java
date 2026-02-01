package com.tushar27x.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EventDTO {
    private String eventId;
    private String userId;
    private String eventType;
    private String source;
    private long timestamp;
}
