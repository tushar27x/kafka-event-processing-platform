package com.tushar27x.producer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventResponseDTO {
    private String userId;
    private String eventId;
    private String source;
    private String eventType;
}
