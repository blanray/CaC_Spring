package com.example.CrudPB.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestTipo {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void  testBuscarTipoId() throws Exception{

        Integer idTemp = 23;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/buscar/tiposPorID/" + idTemp))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

}
