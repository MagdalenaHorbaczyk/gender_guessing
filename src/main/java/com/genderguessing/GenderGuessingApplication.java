package com.genderguessing;

import com.genderguessing.exception.FileReaderException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenderGuessingApplication {

    public static void main(String[] args) throws FileReaderException {

        SpringApplication.run(GenderGuessingApplication.class, args);

//
//UserService service = new UserService();
////Controller controller = new Controller();
//service.guessGenderByFirstToken("Agata Adam");
////controller.guessGenderByFullName("Agata Adam");
////service.checkMaleList("Adam");
////service.guessGenderByFullName("Agata Adam Alina Marcel");
////service.tokenize("Agata Adam");
////service.findAvailableTokens("Agata Alina Adam Marcel");

    }

}
