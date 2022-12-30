package com.example.CrudPB.service;


import com.example.CrudPB.dto.request.UserRequestDTO;
import com.example.CrudPB.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"SCOPE = integration_test"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTestLogin {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Test EP Login")
    public void testLoginOK() throws Exception{

        UserRequestDTO tempDto = new UserRequestDTO("Empleado1", "123");

        ObjectWriter objetoTemp = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE , false)
                .writer().withDefaultPrettyPrinter();

        String tempJson = objetoTemp.writeValueAsString(tempDto);


        String tempJSON = "'userName': 'Empleado1', 'password': '123'";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/CrudPB/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(tempJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
    @Test
    @Order(2)
    @DisplayName("Test EP Login falla")
    public void testLoginFalla() throws Exception{

        UserRequestDTO tempDto = new UserRequestDTO("Empleado1", "Emplead1");

        ObjectWriter objetoTemp = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE , false)
                .writer().withDefaultPrettyPrinter();

        String tempJson = objetoTemp.writeValueAsString(tempDto);


        String tempJSON = "'userName': 'Empleado1', 'password': '123'";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/CrudPB/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
            .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }


}
