package com.genderguessing.service;

import com.genderguessing.exception.FileReaderException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    File maleFile = new File(getMaleFile());
    File femaleFile = new File(getFemaleFile());

    public void guessGenderByFirstToken(String name) throws FileReaderException {
        String firstToken = tokenize(name).get(0);
        if ((checkMaleList(firstToken)) >= 1) {
            System.out.println(name + " is male.");
        } else if ((checkFemaleList(firstToken)) >= 1) {
            System.out.println(name + " is female.");
        } else {
            System.out.println(name + " is inconclusive.");
        }
    }

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

    public int checkMaleList(String firstToken) throws FileReaderException {
        List<String> firstTokenInMaleList;
        try (Stream<String> maleLines = getMaleLines()) {
            firstTokenInMaleList = maleLines
                    .filter(line -> line.contains(firstToken))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException();
        }
        return firstTokenInMaleList.size();
    }

    private Stream<String> getMaleLines() throws IOException {
        return Files.lines(Paths.get(maleFile.getPath()));
    }

    private String getMaleFile() {
        return Objects.requireNonNull(getClassLoader().getResource("file/male.txt")).getFile();
    }

    private int checkFemaleList(String firstToken) throws FileReaderException {
        List<String> firstTokenInFemaleList;
        try (Stream<String> femaleLines = getFemaleLines()) {
            firstTokenInFemaleList = femaleLines
                    .filter(line -> line.contains(firstToken))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException();
        }
        return firstTokenInFemaleList.size();
    }

    private Stream<String> getFemaleLines() throws IOException {
        return Files.lines(Paths.get(femaleFile.getPath()));
    }

    private String getFemaleFile() {
        return Objects.requireNonNull(getClassLoader().getResource("file/female.txt")).getFile();
    }

    private ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    public void guessGenderByFullName(String name) throws FileReaderException {
        List<String> nameTokens = tokenize(name);
        List<String> maleTokensFound = checkMaleListByFullName(nameTokens);
        List<String> femaleTokensFound = checkFemaleListByFullName(nameTokens);
        if (maleTokensFound.size() > (femaleTokensFound.size())
                && (maleTokensFound.size() + femaleTokensFound.size() == nameTokens.size())) {
            System.out.println(name + " is male.");
        } else if (maleTokensFound.size() < femaleTokensFound.size()
                && (maleTokensFound.size() + femaleTokensFound.size() == nameTokens.size())) {
            System.out.println(name + " is female.");
        } else {
            System.out.println(name + " is inconclusive.");
        }
    }

    private List<String> checkMaleListByFullName(List<String> nameTokens) throws FileReaderException {
        List<String> maleTokensFound;
        try (Stream<String> fileLines = getMaleLines()) {
            Set<String> maleSet = new HashSet<>(nameTokens);
            maleTokensFound = fileLines
                    .filter(maleSet::contains)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException();
        }
        return maleTokensFound;
    }

    private List<String> checkFemaleListByFullName(List<String> nameTokens) throws FileReaderException {
        List<String> femaleTokensFound;
        try (Stream<String> fileLinesFemale = getFemaleLines()) {
            Set<String> femaleSet = new HashSet<>(nameTokens);
            femaleTokensFound = fileLinesFemale
                    .filter(femaleSet::contains)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException();
        }
        return femaleTokensFound;
    }

    public List<List> findAvailableTokens(String name) throws FileReaderException {
        List<String> nameTokens = tokenize(name);
        List<List> tokensFound = new ArrayList<List>();
        tokensFound.add(checkMaleListByFullName(nameTokens));
        tokensFound.add(checkFemaleListByFullName(nameTokens));
        System.out.println(tokensFound);
        return tokensFound;
    }
}