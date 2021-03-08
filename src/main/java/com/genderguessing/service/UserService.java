package com.genderguessing.service;

import com.genderguessing.factory.GenderGuesser;
import com.genderguessing.factory.GuessOptionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    GuessOptionFactory factory;

    public GenderGuesser guess(String name) {
        return factory.makeGuess(name);
    }
}