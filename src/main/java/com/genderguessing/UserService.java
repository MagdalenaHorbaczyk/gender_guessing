package com.genderguessing;

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

    public void guessGenderByFirstName(String name) throws FileReaderException {
        String firstName = divide(name).get(0);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("file/male.txt")).getFile());
        try (Stream<String> fileLines = Files.lines(Paths.get(file.getPath()))) {
            long i = fileLines.filter(line -> line.equals(firstName))
                    .count();
            if (i >= 1) {
                System.out.println(firstName + " is male.");
            } else {
                File file1 = new File(Objects.requireNonNull(classLoader.getResource("file/female.txt")).getFile());
                try (Stream<String> fileLines1 = Files.lines(Paths.get(file1.getPath()))) {
                    long j = fileLines1.filter(line -> line.equals(firstName))
                            .count();
                    if (j == 1) {
                        System.out.println(firstName + " is female");
                    }
                } catch (IOException e) {
                    throw new FileReaderException();
                }
            }
        } catch (IOException e) {
            throw new FileReaderException();
        }
    }

    public List<String> divide(String name) {
        List<String> list = new ArrayList<>();
        Scanner givenNameSplittedIntoTokens = new Scanner(name);
        givenNameSplittedIntoTokens.useDelimiter("\s");
        while (givenNameSplittedIntoTokens.hasNext()) {
            list.add((givenNameSplittedIntoTokens.next()));
        }
        givenNameSplittedIntoTokens.close();
        System.out.println(list.size() + list.toString());
        return list;
    }

    public void guessGenderByFullName(String name) throws FileReaderException {
        List<String> listOne = divide(name);
        List<String> listCommonMale;
        List<String> listCommonFemale;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("file/male.txt")).getFile());
        try (Stream<String> maleLines = Files.lines(Paths.get(file.getPath()))) {
            Set<String> setOne = new HashSet<>(listOne);
            listCommonMale = maleLines
                    .filter(e -> setOne.contains(e))
                    .collect(Collectors.toList());
            System.out.println(listCommonMale.toString() + listCommonMale.size());
        } catch (
                IOException e) {
            throw new FileReaderException();
        }
        File fileFemale = new File(Objects.requireNonNull(classLoader.getResource("file/female.txt")).getFile());
        try (Stream<String> femaleLines = Files.lines(Paths.get(fileFemale.getPath()))) {
            Set<String> setTwo = new HashSet<>(listOne);
            listCommonFemale = femaleLines
                    .filter(e -> setTwo.contains(e))
                    .collect(Collectors.toList());
            System.out.println(listCommonFemale.toString() + listCommonFemale.size());
        } catch (
                IOException e) {
            throw new FileReaderException();
        }
        if (listCommonMale.size() > (listCommonFemale.size())
                && (listCommonMale.size() + listCommonFemale.size() == listOne.size())) {
            System.out.println(name + " is male.");
        } else if (listCommonMale.size() < listCommonFemale.size()
                && (listCommonMale.size() + listCommonFemale.size() == listOne.size())) {
            System.out.println(name + " is female.");
        } else {
            System.out.println(name + " is inconclusive.");
        }
    }
}