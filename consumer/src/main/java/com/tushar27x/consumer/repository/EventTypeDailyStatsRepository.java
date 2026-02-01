package com.tushar27x.consumer.repository;


import com.tushar27x.consumer.entity.EventTypeDailyStats;
import com.tushar27x.consumer.entity.EventTypeDailyStatsId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface EventTypeDailyStatsRepository extends JpaRepository<EventTypeDailyStats, EventTypeDailyStatsId> {
    @Modifying
    @Transactional
    @Query(value = """
       INSERT INTO event_type_daily_stats (event_date, event_type, event_count)
       VALUES (:date, :type, 1)
       ON CONFLICT (event_date, event_type)
       DO UPDATE SET event_count = event_type_daily_stats.event_count + 1
       """, nativeQuery = true)
    void increment(LocalDate date, String type);

}
