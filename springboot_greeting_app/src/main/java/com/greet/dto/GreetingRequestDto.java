package com.greet.dto;


public class GreetingRequestDto {

    private String message;

    public GreetingRequestDto() {
    }

    public GreetingRequestDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
