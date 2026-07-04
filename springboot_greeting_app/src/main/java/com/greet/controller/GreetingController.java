package com.greet.controller;

import com.greet.dto.GreetingRequestDto;
import com.greet.dto.GreetingResponseDto;
import com.greet.model.Greeting;
import com.greet.model.User;
import com.greet.repository.UserRepository;
import com.greet.service.GreetingService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService greetingService;
    private final UserRepository userRepository;

    public GreetingController(GreetingService greetingService, UserRepository userRepository) {
        this.greetingService = greetingService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<GreetingResponseDto>> getAllGreetings() {
        List<GreetingResponseDto> greetings = greetingService.findAll().stream()
                .map(this::toResponseDto)
                .toList();
        return ResponseEntity.ok(greetings);
    }

    @PostMapping
    public ResponseEntity<?> createGreeting(
            @RequestBody GreetingRequestDto requestDto,
            @RequestAttribute("username") String username
    ) {
        String message = requestDto.getMessage();
        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Message cannot be empty"));
        }

        User creator = userRepository.findByUsername(username).orElseThrow();
        Greeting greeting = new Greeting();
        greeting.setMessage(message.trim());
        greeting.setCreatedBy(creator);

        Greeting saved = greetingService.save(greeting);
        return ResponseEntity.status(201).body(toResponseDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGreeting(
            @PathVariable("id") Long id,
            @RequestBody GreetingRequestDto requestDto
    ) {
        String message = requestDto.getMessage();
        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Message cannot be empty"));
        }

        return greetingService.findById(id)
                .<ResponseEntity<?>>map(greeting -> {
                    greeting.setMessage(message.trim());
                    Greeting updated = greetingService.save(greeting);
                    return ResponseEntity.ok(toResponseDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGreeting(@PathVariable("id") Long id) {
        return greetingService.findById(id)
                .<ResponseEntity<?>>map(greeting -> {
                    greetingService.delete(id);
                    return ResponseEntity.ok(Map.of("message", "Greeting deleted successfully"));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private GreetingResponseDto toResponseDto(Greeting greeting) {
        return new GreetingResponseDto(
                greeting.getId(),
                greeting.getMessage(),
                greeting.getCreatedBy() != null ? greeting.getCreatedBy().getUsername() : null,
                greeting.getCreatedAt()
        );
    }
}
