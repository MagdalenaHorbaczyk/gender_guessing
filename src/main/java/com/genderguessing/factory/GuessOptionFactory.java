package com.genderguessing.factory;

import org.springframework.stereotype.Component;

@Component
public class GuessOptionFactory {
    public static final String SIMPLE_GUESSER = "SIMPLE_GUESSER";
    public static final String ADVANCED_GUESSER = "ADVANCED_GUESSER";

    public final GenderGuesser makeGuess(final String name) {
        return switch (name) {
            case SIMPLE_GUESSER -> new SimpleGuesser("Adam Agata Alina Nowak");
            case ADVANCED_GUESSER -> new AdvancedGuesser("Adam Agata");
            default -> null;
        };
    }
}