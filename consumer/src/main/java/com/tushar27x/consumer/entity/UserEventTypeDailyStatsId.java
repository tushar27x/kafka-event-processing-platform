package com.tushar27x.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventTypeDailyStatsId {
    private LocalDate eventDate;
    private String userId;
    private String eventType;
}
