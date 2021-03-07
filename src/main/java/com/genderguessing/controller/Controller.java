package com.genderguessing.controller;

import com.genderguessing.exception.FileReaderException;
import com.genderguessing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/gender")
public class Controller {

    @Autowired
    UserService userService;

    @PostMapping("/byFirstName")
    public void guessGenderByFirstName(@RequestBody String name) throws FileReaderException {
        userService.guessGenderByFirstToken(name);
    }

    @PostMapping("/byFullName")
    public void guessGenderByFullName(@RequestBody String name) throws FileReaderException {
        userService.guessGenderByFullName(name);
    }

    @GetMapping("/getNames")
    public List<List> getAvailableTokens(@RequestBody String name) throws FileReaderException {
        return userService.findAvailableTokens(name);
    }
}
