package com.greet.service;

import com.greet.model.Greeting;

public class GreetingServiceImpl implements GreetingService {

    @Override
    public Greeting greet(String name) {

        if (name == null || name.trim().isEmpty()) {
            return new Greeting("Hello World");
        }

        return new Greeting("Hello " + name);
    }
}