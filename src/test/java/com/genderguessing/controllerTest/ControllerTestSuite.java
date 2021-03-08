package com.genderguessing.controllerTest;

import com.genderguessing.controller.Controller;
import com.genderguessing.exception.FileReaderException;
import com.genderguessing.service.UserService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTestSuite {
    @MockBean
    UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void guessGenderByFirstNameTest() throws Exception, FileReaderException {
        //Given
        String name = "Adam Aleksander Nowak";
        when(userService.guessGenderByFirstToken(name)).thenReturn(name);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(name);
        //When & Then
        mockMvc.perform(post("/gender/byFirstName")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));

        /*for further  investigation*/
//                .andExpect(jsonPath("$.name", is("Adam Aleksander Nowak is male.")));
    }

//    @Test
//    public void getAvailableTokensTest() throws Exception, FileReaderException {
//        //Given
//        String name = "Adam Aleksander Alina";
//        List<List> tokensFound = new ArrayList<List>();
//        ArrayList<String> maleList = new ArrayList<>();
//        ArrayList<String> femaleList = new ArrayList<>();
//        maleList.add("Adam");
//        femaleList.add("Aleksander");
//        femaleList.add("Alina");
//        tokensFound.add(maleList);
//        tokensFound.add(femaleList);
//        when(userService.findAvailableTokens(name)).thenReturn(tokensFound);
//        //When & Then
//        mockMvc.perform(get("/gender/getNames")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(3)));
//                .andExpect(jsonPath("$[0].name", is("Adam Aleksander Alina")));
}

