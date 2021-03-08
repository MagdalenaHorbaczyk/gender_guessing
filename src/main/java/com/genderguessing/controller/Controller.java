package com.genderguessing.controller;

import com.genderguessing.factory.GenderGuesser;
import com.genderguessing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/gender")
public class Controller {
    @Autowired
    UserService userService;

    @PostMapping("/byName")
    public GenderGuesser guessGender(@RequestBody String name) {
        return userService.guess(name);
    }
//
//    @PostMapping("/byFullName")
//    public String guessGenderByFullName(@RequestBody String name) throws FileReaderException {
//        return userService.guessGenderByFullName(name);
//    }
//
//    @GetMapping("/getNames")
//    public List<List> getAvailableTokens(@RequestBody String name) throws FileReaderException {
//        return userService.findAvailableTokens(name);
//    }
}
