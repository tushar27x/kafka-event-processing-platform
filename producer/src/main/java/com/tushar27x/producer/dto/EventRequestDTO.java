package com.tushar27x.producer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EventRequestDTO {

    private String eventId;

    @NotBlank(message = "userId is required")
    private String userId;

    @NotBlank(message = "source is required")
    private String source;

    @NotBlank(message = "eventType is required")
    private String eventType;

}
