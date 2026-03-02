package com.campconnect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.campconnect.enums.DifficultyLevel;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private String id;
    private String title;
    private String description;

    @DBRef
    private Category category; 

    private String difficulty;
    private int duration;
    private int enrolledCount;
    private double rating;
    private int reviews;
    private double price;
    private String imageUrl;
    private List<String> tags;
    private List<String> prerequisites;
    private int passingScore;

    @DBRef
    private User creator;

    @DBRef
    private User instructor;
}
