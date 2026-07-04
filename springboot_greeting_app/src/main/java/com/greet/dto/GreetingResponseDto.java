package com.greet.dto;

import java.time.LocalDateTime;

public class GreetingResponseDto {

    private Long id;
    private String message;
    private String createdByUsername;
    private LocalDateTime createdAt;

    public GreetingResponseDto() {
    }

    public GreetingResponseDto(Long id, String message, String createdByUsername, LocalDateTime createdAt) {
        this.id = id;
        this.message = message;
        this.createdByUsername = createdByUsername;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
