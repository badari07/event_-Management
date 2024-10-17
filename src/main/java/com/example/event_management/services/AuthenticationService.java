package com.example.event_management.services;

import com.example.event_management.dto.RegisterUserDto;
import com.example.event_management.dto.LoginUserDto;
import com.example.event_management.exceptions.BadRequestException;
import com.example.event_management.exceptions.NotFoundException;
import com.example.event_management.exceptions.PasswordEncodingException;
import com.example.event_management.exceptions.UnauthorizedException;
import com.example.event_management.models.User;
import com.example.event_management.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(@Valid RegisterUserDto input) {

        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already in use"); // or use a custom exception
        }
        User user = new User();
                user.setName(input.getName());
                user.setEmail(input.getEmail());
                user.setPassword(encodePassword(input.getPassword()));

        logger.info("User {} successfully registered.", input.getEmail());

        return userRepository.save(user);
    }

    private String encodePassword(String password) {
        try {
            return passwordEncoder.encode(password);
        } catch (Exception e) {
            logger.error("Error encoding password: {}", e.getMessage(), e);
            throw new PasswordEncodingException("Failed to encode password.");
        }
    }


    public User authenticate(@Valid LoginUserDto input) {
        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + input.getEmail() + ". Please register first."));

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    )
            );

            logger.info("User {} authenticated successfully.", input.getEmail());
            return user;

        } catch (NotFoundException e) {
            throw e;
             }
             catch (
                BadCredentialsException e) {
            logger.warn("Authentication failed for user: {}", input.getEmail());
            throw new UnauthorizedException("Invalid email or password. Please try again.");
        } catch (Exception e) {
            logger.error("Unexpected error during authentication: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred during authentication: " + e.getMessage());
        }

    }
}

