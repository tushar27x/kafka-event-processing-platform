package com.tushar27x.consumer.repository;

import com.tushar27x.consumer.entity.UserEventTypeDailyStats;
import com.tushar27x.consumer.entity.UserEventTypeDailyStatsId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface UserEventTypeDailyStatRepository extends JpaRepository<UserEventTypeDailyStats, UserEventTypeDailyStatsId> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO user_event_type_daily_stats (event_date, user_id, event_type, event_count)
        VALUES (:date, :user, :eventType, 1)
        ON CONFLICT (event_date, user_id, event_type)
        DO UPDATE SET event_count = user_event_type_daily_stats.event_count + 1
        """, nativeQuery = true)
    void increment(LocalDate date, String user, String eventType);
}
