package com.genderguessing.factory;

import com.genderguessing.exception.FileReaderException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class AdvancedGuesser implements GenderGuesser {
    Male male;
    Female female;

    public AdvancedGuesser(final String name) {
    }

    @Override
    public String guessGender(String name) throws FileReaderException {
        String result;
        List<String> nameTokens = tokenize(name);
        List<String> maleTokensFound = male.checkListByFullName(nameTokens);
        List<String> femaleTokensFound = female.checkListByFullName(nameTokens);
        if (maleTokensFound.size() > (femaleTokensFound.size())
                && (maleTokensFound.size() + femaleTokensFound.size() == nameTokens.size())) {
            result = name + " is male.";
        } else if (maleTokensFound.size() < femaleTokensFound.size()
                && (maleTokensFound.size() + femaleTokensFound.size() == nameTokens.size())) {
            result = name + " is female.";
        } else {
            result = name + " is inconclusive.";
        }
        System.out.println(result);
        return result;
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
    public boolean isNameFound() {
        return false;
    }
}

