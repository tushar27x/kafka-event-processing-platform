package com.tushar27x.consumer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user_event_type_daily_stats")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserEventTypeDailyStatsId.class)
public class UserEventTypeDailyStats {

    @Id
    private LocalDate eventDate;

    @Id
    private String userId;

    @Id
    private String eventType;

    private long eventCount;
}
