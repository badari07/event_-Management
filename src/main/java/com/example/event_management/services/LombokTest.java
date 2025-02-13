package com.example.event_management.services;

import com.example.event_management.dto.LoginResponseDto;

public class LombokTest {
    public static void main(String[] args) {
        LoginResponseDto response = new LoginResponseDto()
                .setToken("test-token")
                .setExpiresIn(3600L);

        System.out.println("Token: " + response.getToken());
        System.out.println("Expires In: " + response.getExpiresIn());
    }
}