package com.campconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {
    private String id;
    private String title;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private String category;
    private String type;
    private int views;
    private int helpfulCount;
    private LocalDateTime createdAt;
    private UserSummaryDTO creator;
    private List<String> takeaways;
    private List<CommentDTO> comments;
}
