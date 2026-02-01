package com.tushar27x.consumer.dto;

import lombok.Data;

@Data
public class EventDTO {
    private String eventId;
    private String userId;
    private String source;
    private String eventType;
    private long timestamp;
}
