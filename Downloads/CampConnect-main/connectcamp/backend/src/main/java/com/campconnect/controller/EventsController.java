package com.campconnect.controller;

import com.campconnect.dto.EventDTO;
import com.campconnect.services.IEventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventsController {

    @Autowired
    private IEventServices eventService;

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable String id) {
        EventDTO event = eventService.getEventById(id);
        return event != null ? ResponseEntity.ok(event) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable String id, @Valid @RequestBody EventDTO eventDTO) {
        EventDTO updated = eventService.updateEvent(id, eventDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<com.campconnect.dto.EventRegistrationDTO> registerForEvent(
            @PathVariable String id,
            @RequestParam String userId,
            @RequestParam(defaultValue = "1") int participants) {
        return ResponseEntity.ok(eventService.registerUser(id, userId, participants));
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<com.campconnect.dto.EventRegistrationDTO>> getParticipants(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventParticipants(id));
    }
}
