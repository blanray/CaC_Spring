package com.example.CrudPB.service;

import org.junit.jupiter.api.*;
import com.example.CrudPB.dto.response.ProductoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class IntegrationTestProducto {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Test EP Listar Productos")
    public void testListarProductos() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/listar/productos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(2)
    @DisplayName("Test EP Crear Producto")
    public void testCrearTipo() throws Exception{

        ProductoDto productoTemp = new ProductoDto(0, "Prueba desde Test", 7, 33, 33);

        ObjectWriter objetoTemp = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE , false)
                .writer().withDefaultPrettyPrinter();

        String tempJson = objetoTemp.writeValueAsString(productoTemp);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/crear/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(3)
    @DisplayName("Test EP Buscar Producto por ID")
    public void  testBuscarProductoId() throws Exception{

        Integer idTemp = 10;


        this.mockMvc.perform(MockMvcRequestBuilders.get("/buscar/productosID/" + idTemp))
                .andDo(print())
                //        .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @Test
    @Order(4)
    @DisplayName("Test EP Actualizar Producto por ID")
    public void testActualizarProductoId() throws Exception{

        ProductoDto productoTemp = new ProductoDto(2, "Prueba desde Test", 10, 33, 33);
        ObjectWriter objetoTemp = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE , false)
                .writer().withDefaultPrettyPrinter();

        String tempJson = objetoTemp.writeValueAsString(productoTemp);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/actualizar/PorID/" + productoTemp.getPrd_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempJson))
                .andDo(print())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(5)
    @DisplayName("Test EP Borrar Producto ID")
    public void testBorrarProductoId() throws Exception{

        Integer idTemp = 11;

        this.mockMvc.perform(MockMvcRequestBuilders.post("/borrar/producto/" + idTemp))
                .andDo(print())
                //        .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(8)
    @DisplayName("Test EP Borrar All Productos")
    public void testBorrarAllProducto() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/borrar/productos"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(6)
    @DisplayName("Test EP Top 5 Stock - Consigna 1")
    public void testListarProductosTop5() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/listar/productos/top5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(7)
    @DisplayName("Test EP Buscar Productos por Tipo - Consigna 2")
    public void  testBuscarProductoTipoId() throws Exception{

        Integer idTemp = 8;


        this.mockMvc.perform(MockMvcRequestBuilders.get("/listar/productosTipo/" + idTemp))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(9)
    @DisplayName("Test EP Crear Producto con error en el Body para validacion")
    public void testCrearTipoConError() throws Exception{

        ProductoDto productoTemp = new ProductoDto(0, "P", 7, -1, -1);

        ObjectWriter objetoTemp = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE , false)
                .writer().withDefaultPrettyPrinter();

        String tempJson = objetoTemp.writeValueAsString(productoTemp);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/crear/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }


}
