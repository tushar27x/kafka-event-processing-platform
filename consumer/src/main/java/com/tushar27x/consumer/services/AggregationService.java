package com.tushar27x.consumer.services;

import com.tushar27x.consumer.dto.EventDTO;
import com.tushar27x.consumer.repository.EventTypeDailyStatsRepository;
import com.tushar27x.consumer.repository.UserDailyStatsRepository;
import com.tushar27x.consumer.repository.UserEventTypeDailyStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AggregationService {

    private final EventTypeDailyStatsRepository eventTypeRepo;
    private final UserDailyStatsRepository userRepo;
    private final UserEventTypeDailyStatRepository userEventRepo;

    public void aggregate(EventDTO event) {
        LocalDate date = Instant.ofEpochMilli(event.getTimestamp())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        eventTypeRepo.increment(date, event.getEventType());
        userRepo.increment(date, event.getUserId());
        userEventRepo.increment(date, event.getUserId(), event.getEventType());
    }
}
