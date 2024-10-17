package com.example.event_management.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email cannot be null or empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @Size(max = 20, message = "Password must be no more than 20 characters long")
    private String password;
}
