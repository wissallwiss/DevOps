package com.campconnect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.campconnect.enums.DifficultyLevel;
import com.campconnect.enums.TripStatus;
import java.time.LocalDateTime;

@Document(collection = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    private String id;
    private String destination;
    private DifficultyLevel difficulty;
    private TripStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @DBRef
    private Category category;

    @DBRef
    private User creator;
}
