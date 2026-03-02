package com.campconnect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videos")
public class Video {
    @Id
    private String id;
    private String title;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private String category; // survival, navigation, etc.
    private String type; // REEL, EXPERIENCE, TUTORIAL
    private int views;
    private int helpfulCount;
    private LocalDateTime createdAt;

    @DBRef
    private User creator;

    private List<String> takeaways = new ArrayList<>();
    
    @DBRef
    private List<Comment> comments = new ArrayList<>();
}
