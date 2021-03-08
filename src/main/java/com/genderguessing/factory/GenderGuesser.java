package com.genderguessing.factory;

import com.genderguessing.exception.FileReaderException;

import java.util.List;

public interface GenderGuesser {
    String guessGender(String name) throws FileReaderException;

    List<List> findAvailableTokens(String name) throws FileReaderException;

    List<String> tokenize(String name);

    boolean isNameFound();
}
