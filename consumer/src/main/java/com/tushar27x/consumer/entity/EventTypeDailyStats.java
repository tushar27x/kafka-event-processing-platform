package com.tushar27x.consumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "event_type_daily_stats")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EventTypeDailyStatsId.class)
public class EventTypeDailyStats {

    @Id
    private LocalDate eventDate;

    @Id
    private String eventType;

    private long eventCount;
}
