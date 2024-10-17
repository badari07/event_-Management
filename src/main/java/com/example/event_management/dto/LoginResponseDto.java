package com.example.event_management.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LoginResponseDto {

    private String token;

    private long expiresIn;

}
