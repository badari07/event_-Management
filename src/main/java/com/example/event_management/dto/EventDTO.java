package com.example.event_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDate date;
    private UserDTO creator;

    public EventDTO(Long id, String title, String description,String location, LocalDate date, UserDTO creator) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.creator = creator;
    }
}
