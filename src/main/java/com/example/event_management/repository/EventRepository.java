package com.example.event_management.repository;

import com.example.event_management.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository  extends JpaRepository<Event, Long> {

}
