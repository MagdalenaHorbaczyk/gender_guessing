package com.genderguessing.factory;

import com.genderguessing.exception.FileReaderException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Male implements Gender {

    File maleFile = new File(getFile());

    @Override
    public String getFile() {
        return Objects.requireNonNull(getClassLoader().getResource("file/male.txt")).getFile();
    }

    @Override
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public int checkList(String name, String firstToken) throws FileReaderException {
        List<String> firstTokenInMaleList;
        try (Stream<String> maleLines = getLines()) {
            firstTokenInMaleList = maleLines
                    .filter(line -> line.contains(firstToken))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException();
        }
        return firstTokenInMaleList.size();
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
    public Stream<String> getLines() throws IOException {
        return Files.lines(Paths.get(maleFile.getPath()));
    }

    @Override
    public List<String> checkListByFullName(List<String> nameTokens) throws FileReaderException {
        List<String> maleTokensFound;
        try (Stream<String> fileLines = getLines()) {
            Set<String> maleSet = new HashSet<>(nameTokens);
            maleTokensFound = fileLines
                    .filter(maleSet::contains)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException();
        }
        return maleTokensFound;
    }
}