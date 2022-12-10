package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.TipoProductoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestProducto {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListarProductos() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/listar/productos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testCrearTipo() throws Exception{

        ProductoDto productoTemp = new ProductoDto(0, "Prueba desde Test", 0, 33, 33);

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
    public void  testBuscarProductoId() throws Exception{

        Integer idTemp = 32;


        this.mockMvc.perform(MockMvcRequestBuilders.get("/buscar/productosID/" + idTemp))
                .andDo(print())
                //        .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @Test
    public void testActualizarProductoId() throws Exception{

        ProductoDto productoTemp = new ProductoDto(0, "Prueba desde Test", 0, 33, 33);
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
    public void testBorrarProductoId() throws Exception{

        Integer idTemp = 40;

        this.mockMvc.perform(MockMvcRequestBuilders.post("/borrar/producto/" + idTemp))
                .andDo(print())
                //        .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testBorrarAllProducto() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/borrar/productos"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testListarProductosTop5() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/listar/productos/top5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void  testBuscarProductoTipoId() throws Exception{

        Integer idTemp = 38;


        this.mockMvc.perform(MockMvcRequestBuilders.get("/listar/productosTipo/" + idTemp))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }


}
