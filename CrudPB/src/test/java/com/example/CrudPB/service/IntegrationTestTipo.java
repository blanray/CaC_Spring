package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.TipoProductoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestTipo {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListarTipos() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/listar/tiposProductos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void  testBuscarTipoId() throws Exception{

        Integer idTemp = 23;


        this.mockMvc.perform(MockMvcRequestBuilders.get("/buscar/tiposPorID/" + idTemp))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testCrearTipo() throws Exception{

        TipoProductoDto tipoTemp = new TipoProductoDto(0, "Prueba desde Test");

        ObjectWriter objetoTemp = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE , false)
                .writer().withDefaultPrettyPrinter();

        String tempJson = objetoTemp.writeValueAsString(tipoTemp);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/crear/tiposProductos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));
    }

}
