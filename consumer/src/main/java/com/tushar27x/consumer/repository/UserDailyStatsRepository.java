package com.tushar27x.consumer.repository;


import com.tushar27x.consumer.entity.UserDailyStats;
import com.tushar27x.consumer.entity.UserDailyStatsId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface UserDailyStatsRepository extends JpaRepository<UserDailyStats, UserDailyStatsId> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO user_daily_stats (event_date, user_id, event_count)
        VALUES (:date, :user, 1)
        ON CONFLICT (event_date, user_id)
        DO UPDATE SET event_count = user_daily_stats.event_count + 1
        """, nativeQuery = true)
    void increment(LocalDate date, String user);
}
