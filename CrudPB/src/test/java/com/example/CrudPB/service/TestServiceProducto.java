package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.entities.Producto;
import com.example.CrudPB.repository.ProductoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {"SCOPE = integration_test"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestServiceProducto {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    @Order(1)
    @DisplayName("Test Servicio Find All")
    void probarFindAll(){

        List<Producto> listaTemp = productoRepository.findAll();

        when(productoRepository.findAll()).thenReturn(listaTemp);
        List<ProductoDto> listaResultado = productoService.listarProductos();

        Assertions.assertAll(()->{
            assertEquals(listaTemp.size(),listaResultado.size());
        });
    }

    @Test
    @Order(2)
    @DisplayName("Test Servicio Buscar Producto por ID")
    void probarFindByID(){

        Integer idTemp = 2;
        Optional<Producto> miTemp = Optional.of(new Producto());

        when(productoRepository.findById(idTemp)).thenReturn(miTemp);
        Optional<ProductoDto> prodResultado = Optional.of(productoService.encontrarProductoPorID(idTemp));

        Assertions.assertAll(()->{
            assertEquals(miTemp.get().getPrd_descripcion(), prodResultado.get().getPrd_descripcion());
        });
    }

    @Test
    @Order(3)
    @DisplayName("Test Servicio Borrar All")
    void probarDeleteAllProductos(){

        SuccessDto dtoResult = productoService.borrarAllProductos();
        SuccessDto dtoExpected = new SuccessDto("Se ha vaciado exitosamente la lista de Productos");

        Assertions.assertAll(()->{
            assertEquals(dtoExpected, dtoResult);
        });
    }

    @Test
    @Order(4)
    @DisplayName("Test Servicio Top 5 Mayor Stock")
    void probarTop5(){

        List<ProductoDto> listaResultado = productoService.topMayorStock();

        Assertions.assertAll(()->{
            assertNotNull(listaResultado);
        });
    }

    @Test
    @Order(5)
    @DisplayName("Test Servicio Precio Por Tipo")
    void probarPrecioPorTipo(){

        Integer idTemp = 8;

        List<ProductoDto> listaResultado = productoService.precioPorTipo(idTemp);

        Assertions.assertAll(()->{
            assertNotNull(listaResultado);
        });
    }


}
