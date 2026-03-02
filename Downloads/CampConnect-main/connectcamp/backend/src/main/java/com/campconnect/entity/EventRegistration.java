package com.campconnect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Document(collection = "event_registrations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRegistration {
    @Id
    private String id;
    
    @DBRef
    private User user;
    
    @DBRef
    private Event event;
    
    private LocalDateTime registrationDate;
    private String status; // e.g., "CONFIRMED", "CANCELLED"
    private int participants;
}
