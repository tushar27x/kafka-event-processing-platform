package com.tushar27x.producer.controller;

import com.tushar27x.producer.dto.EventDTO;
import com.tushar27x.producer.dto.EventRequestDTO;
import com.tushar27x.producer.dto.EventResponseDTO;
import com.tushar27x.producer.service.EvenProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EvenProducerService producerService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> publish(@Valid @RequestBody EventRequestDTO request) {

        String eventId = request.getEventId() != null
                ? request.getEventId()
                : UUID.randomUUID().toString();

        EventDTO eventDTO = EventDTO.builder()
                .eventId(eventId)
                .eventType(request.getEventType())
                .userId(request.getUserId())
                .source(request.getSource())
                .timestamp(System.currentTimeMillis())
                .build();

        producerService.send(eventDTO);

        return ResponseEntity.accepted().body(
                EventResponseDTO.builder()
                        .eventId(eventDTO.getEventId())
                        .eventType(eventDTO.getEventType())
                        .userId(eventDTO.getUserId())
                        .source(eventDTO.getSource())
                        .build()
        );
    }

}
