package com.campconnect.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRegistrationDTO {
    private String id;
    private String userId;
    private String username;
    private String eventId;
    private String eventTitle;
    private LocalDateTime registrationDate;
    private String status;
    private int participants;
}
