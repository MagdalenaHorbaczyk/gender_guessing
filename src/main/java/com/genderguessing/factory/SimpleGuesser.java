package com.genderguessing.factory;

import com.genderguessing.exception.FileReaderException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class SimpleGuesser implements GenderGuesser {
    private final String name;
    Male male;
    Female female;

    public SimpleGuesser(final String name) {
        this.name = name;
    }

    @Override
    public String guessGender(String name) throws FileReaderException {
        String firstToken = tokenize(name).get(0);
        String result;
        if ((male.checkList(name, firstToken)) >= 1) {
            result = name + " is male.";
        } else if ((female.checkList(name, firstToken)) >= 1) {
            result = name + " is female.";
            System.out.println(name + " is female.");
        } else {
            result = name + " is inconclusive.";
        }
        System.out.println(result);
        return result;
    }

    @Override
    public List<String> tokenize(String name) {
        List<String> nameTokenList = new ArrayList<>();
        Scanner joinedName = new Scanner(name);
        joinedName.useDelimiter("\s");
        while (joinedName.hasNext()) {
            nameTokenList.add((joinedName.next()));
        }
        joinedName.close();
        return nameTokenList;
    }

    @Override
    public List<List> findAvailableTokens(String name) throws FileReaderException {
        List<String> nameTokens = tokenize(name);
        List<List> allTokensFound = new ArrayList<List>();
        allTokensFound.add(male.checkListByFullName(nameTokens));
        allTokensFound.add(female.checkListByFullName(nameTokens));
        System.out.println(allTokensFound);
        return (allTokensFound);
    }

    @Override
    public boolean isNameFound() {
        return false;
    }
}

