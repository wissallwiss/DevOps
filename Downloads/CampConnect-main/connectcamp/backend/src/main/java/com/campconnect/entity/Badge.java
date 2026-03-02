package com.campconnect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.campconnect.enums.BadgeRarity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "badges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Badge {
    @Id
    private String id;
    private String name;
    private String description;
    private String icon;

    @DBRef
    private Category category;

    private String rarity;
    private List<String> requirements;

    @DBRef
    private User creator;
}
