package com.genderguessing.factory;

import com.genderguessing.exception.FileReaderException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Female implements Gender {

    File femaleFile = new File(getFile());

    public String getFile() {
        return Objects.requireNonNull(getClassLoader().getResource("file/female.txt")).getFile();
    }

    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public int checkList(String name, String firstToken) throws FileReaderException {
        List<String> firstTokenInFemaleList;
        try (Stream<String> femaleLines = getLines()) {
            firstTokenInFemaleList = femaleLines
                    .filter(line -> line.equals(firstToken))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException();
        }
        return firstTokenInFemaleList.size();
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

    public Stream<String> getLines() throws IOException {
        return Files.lines(Paths.get(femaleFile.getPath()));
    }

    public List<String> checkListByFullName(List<String> nameTokens) throws FileReaderException {
        List<String> femaleTokensFound;
        try (Stream<String> fileLinesFemale = getLines()) {
            Set<String> setFemale = new HashSet<>(nameTokens);
            femaleTokensFound = fileLinesFemale
                    .filter(setFemale::contains)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException();
        }
        return femaleTokensFound;
    }
}

