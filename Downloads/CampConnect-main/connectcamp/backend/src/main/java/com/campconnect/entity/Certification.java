package com.campconnect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Document(collection = "certifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> requirements;
    private int validityPeriod; // in months
    private String imageUrl;
    private String issuer;

    @DBRef
    private User creator;

    @DBRef
    private List<Course> requiredCourses;
}
