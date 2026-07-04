package com.greet.service;

import com.greet.model.Greeting;
import com.greet.repository.GreetingRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    private static final Logger log = LoggerFactory.getLogger(GreetingServiceImpl.class);

    private final GreetingRepository greetingRepository;

    public GreetingServiceImpl(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public Greeting save(Greeting greeting) {
        log.info("Saving greeting: '{}'", greeting.getMessage());
        return greetingRepository.save(greeting);
    }

    @Override
    public List<Greeting> findAll() {
        log.debug("Retrieving all greetings from H2 database");
        return greetingRepository.findAll();
    }

    @Override
    public Optional<Greeting> findById(Long id) {
        log.debug("Querying greeting by ID: {}", id);
        return greetingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.warn("Deleting greeting with ID: {}", id);
        greetingRepository.deleteById(id);
    }
}
