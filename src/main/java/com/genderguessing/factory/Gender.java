package com.genderguessing.factory;

import com.genderguessing.exception.FileReaderException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface Gender {

    String getFile();

    List<String> tokenize(String name);

    ClassLoader getClassLoader();

    int checkList(String name, String firstToken) throws FileReaderException;

    Stream<String> getLines() throws IOException;

    List<String> checkListByFullName(List<String> nameTokens) throws FileReaderException;
}
