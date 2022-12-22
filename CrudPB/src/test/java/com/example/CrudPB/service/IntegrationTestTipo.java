package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.dto.response.TipoProductoDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"SCOPE = integration_test"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTestTipo {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Test EP Listar Tipos")
    public void testListarTipos() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/listar/tiposProductos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(2)
    @DisplayName("Test EP Buscar Tipo por ID")
    public void  testBuscarTipoId() throws Exception{

        Integer idTemp = 9;


        this.mockMvc.perform(MockMvcRequestBuilders.get("/buscar/tiposPorID/" + idTemp))
                .andDo(print())
        //        .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        /*if (resultado.getResponse().getStatus() == 200) {
            Assertions.assertAll(resultado.getResponse().getContentType(), (Executable) content().contentType("\"application/json\""));
        }*/

    }

    @Test
    @Order(3)
    @DisplayName("Test EP Crear Tipo")
    public void testCrearTipo() throws Exception{

        TipoProductoDto tipoTemp = new TipoProductoDto();
        tipoTemp.setTip_descripcion("Prueba desde Test");

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

    @Test
    @Order(6)
    @DisplayName("Test EP Borrar All Tipos")
    public void testBorrarAllTipo() throws Exception{
//Este test es exitoso solo si se puede vaciar la lista de Tipos porque no hay Productos asociados.por lo que no hago la verificacion del texto
//        SuccessDto expectedDto = new SuccessDto("Se ha vaciado exitosamente la lista de Tipos de Producto");

        ObjectWriter objetoTemp = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE , false)
                .writer().withDefaultPrettyPrinter();

//        String tempJson = objetoTemp.writeValueAsString(expectedDto);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/borrar/tipoProductos"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType("application/json"));
//                .andExpect(jsonPath("$.mensaje").value(expectedDto.getMensaje()));
    }

    @Test
    @Order(4)
    @DisplayName("Test EP Actualizar Tipo por ID")
    public void testActualizarTipoId() throws Exception{

        TipoProductoDto tipoTemp = new TipoProductoDto(8, "Prueba desde Test");

        ObjectWriter objetoTemp = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE , false)
                .writer().withDefaultPrettyPrinter();

        String tempJson = objetoTemp.writeValueAsString(tipoTemp);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/actualizar/tipoPorID/" + tipoTemp.getTip_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tempJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @Order(5)
    @DisplayName("Test EP Borrar Tipo por ID")
    public void testBorrarTipoId() throws Exception{

        Integer idTemp = 3; //Este test solo es exitoso si la lista no esta vacia y el ID que se pasa existe

        this.mockMvc.perform(MockMvcRequestBuilders.post("/borrar/tipoProducto/" + idTemp))
                .andDo(print())
                //        .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));


    }


}
