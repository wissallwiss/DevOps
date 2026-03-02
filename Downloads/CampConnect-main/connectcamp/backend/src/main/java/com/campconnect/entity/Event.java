package com.campconnect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.campconnect.enums.EventStatus;
import com.campconnect.enums.DifficultyLevel;
import com.campconnect.enums.EventType;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    private String id;
    private String title;
    private String description;
    private String type;
    private Location location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int duration;
    private int capacity;
    private int registered;
    private double price;
    private String difficulty;
    private List<String> tags;
    private String imageUrl;
    private String status;
    private List<String> whatToExpect;
    private List<String> whatToBring;
    private List<String> whatsIncluded;
    private List<String> safetyNotes;
    private String cancellationPolicy;

    @DBRef
    private Category category;

    @DBRef
    private User creator;
}
