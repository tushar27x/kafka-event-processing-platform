package com.tushar27x.producer.service;

import com.tushar27x.producer.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvenProducerService {

    private static final String TOPIC = "user.activity.events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(EventDTO event) {
        kafkaTemplate.send(TOPIC, event.getUserId(), event);
    }
}
