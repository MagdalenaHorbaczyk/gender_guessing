package com.genderguessing.serviceTest;

import com.genderguessing.exception.FileReaderException;
import com.genderguessing.service.UserService;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTestSuite {
    //given
    String name = "Adam Aleksander Agata Nowak";
    @Autowired
    private UserService userService;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Test Suite: begin");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Test Suite: end");
    }

    @Before
    public void before() {
        System.out.println("Test Case: begin");
    }

    @After
    public void after() {
        System.out.println("Test Case: end");
    }

    @Test
    public void guessGenderByFirstTokenTest() throws FileReaderException {
        //when
        String guessedGender = userService.guessGenderByFirstToken(name);
        //then
        Assert.assertEquals(guessedGender, "Adam Aleksander Agata Nowak is male.");
    }

    @Test
    public void tokenizeTest() {
        //when
        String firstToken = userService.tokenize(name).get(0);
        //then
        Assert.assertEquals("Adam", firstToken);
    }

    @Test
    public void maleAndFemaleListByFirstTokenTest() throws FileReaderException {
        //given
        String firstToken = userService.tokenize(name).get(0);
        //when
        int firstTokenInMaleList = userService.checkMaleList(firstToken);
        int firstTokenInFemaleList = userService.checkFemaleList(firstToken);
        //then
        Assertions.assertEquals(1, firstTokenInMaleList);
        Assertions.assertEquals(0, firstTokenInFemaleList);
    }

    @Test
    public void guessGenderByFullNameTest() throws FileReaderException {
        //when
        String guessedGender = userService.guessGenderByFullName(name);
        //then
        Assert.assertEquals(guessedGender, "Adam Aleksander Agata Nowak is inconclusive.");
    }

    @Test
    public void maleAndFemaleListByFullNameTest() throws FileReaderException {
        List<String> nameTokens = userService.tokenize(name);
        //when
        int tokensOnMaleList = userService.checkMaleListByFullName(nameTokens).size();
        int tokensOnFemaleList = userService.checkFemaleListByFullName(nameTokens).size();
        //then
        Assertions.assertEquals(2, tokensOnMaleList);
        Assertions.assertEquals(1, tokensOnFemaleList);
    }
}


