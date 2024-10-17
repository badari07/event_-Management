package com.example.event_management.services;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.UserDTO;
import com.example.event_management.exceptions.NotFoundException;
import com.example.event_management.exceptions.UnauthorizedException;
import com.example.event_management.models.Event;
import com.example.event_management.models.User;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository; // Assume this is available for fetching the creator

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User creator = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));

        event.setCreator(creator);
        logger.info("Event to be created: {}", event);
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, @Valid Event updatedEvent) {

        // Check if the current user is the creator
        Event event = validateCreatorAuthorization(id);

        // Update event details
        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setDate(updatedEvent.getDate());
        event.setLocation(updatedEvent.getLocation());

        logger.info("Event updated successfully: ID = {}", id);
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = validateCreatorAuthorization(id); // Reuse the authorization check

        eventRepository.delete(event);
        logger.info("Event deleted successfully: ID = {}", id);
    }

    private Event validateCreatorAuthorization(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found with ID: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User creator = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + username));

        if (!event.getCreator().equals(creator)) {
            throw new UnauthorizedException("You are not authorized to modify this event");
        }
        return event;
    }
    }

