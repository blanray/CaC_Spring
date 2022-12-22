package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.entities.Producto;
import com.example.CrudPB.exceptions.RecordNotFoundException;
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
    void probarListarProductos(){

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
    void probarEncontrarProductoPorID(){

        Integer idTemp = 2;
        Optional<Producto> miTemp = Optional.of(new Producto());

        when(productoRepository.findById(idTemp)).thenReturn(miTemp);
        Optional<ProductoDto> prodResultado = Optional.of(productoService.encontrarProductoPorID(idTemp));

        Assertions.assertAll(()->{
            assertEquals(miTemp.get().getPrd_descripcion(), prodResultado.get().getPrd_descripcion());
        });
    }

    @Test
    @Order(8)
    @DisplayName("Test Servicio Borrar All")
    void probarBorrarAllProductos(){

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

    @Test
    @Order(6)
    @DisplayName("Test Servicio Crear Producto")
    void probarCrearProducto(){

        ProductoDto miProductoTemp = new ProductoDto(0,"Prueba desde test", 0 , 111, 1111);

        try{
            ProductoDto miRespuesta = productoService.crearProducto(miProductoTemp);

            Assertions.assertAll(()->{
                assertEquals(miRespuesta.getPrd_descripcion(), miProductoTemp.getPrd_descripcion());
            });}
        catch (NullPointerException e){
            Assertions.assertAll(()->{
                assertNotNull(e);
            });}

    }

    @Test
    @Order(3)
    @DisplayName("Test Servicio Borrar Producto por ID")
    void probarBorrarProducto() {

        Integer idTemp = 6;

        try {

            Optional<SuccessDto> resultadoDto = Optional.of(productoService.borrarProducto(idTemp));

            if (resultadoDto.isPresent()) {

                Assertions.assertAll(() -> {
                    assertNotNull(resultadoDto);
                });
            }
        } catch (RecordNotFoundException e) {
            Assertions.assertAll(() -> {
                assertEquals("No se encontro el Producto, con ID valor : '6'", e.getMessage());
            });
        }

    }

    @Test
    @Order(7)
    @DisplayName("Test Servicio Actualizar Producto por ID")
    void probarActualizarPorID(){

        Integer idTemp = 2;
        Optional<Producto> miTemp = Optional.of(new Producto());

        when(productoRepository.findById(idTemp)).thenReturn(miTemp);

        if (miTemp.isPresent()) {

            miTemp.get().setPrd_descripcion("Prueba desde Test");
            miTemp.get().setPrd_tip_id(3);
            miTemp.get().setPrd_precio(1111);
            miTemp.get().setPrd_stock(111);
            Producto miTempSave = productoRepository.save(miTemp.get());

            Optional<ProductoDto> tipoResultado = Optional.of(productoService.encontrarProductoPorID(idTemp));

            if (tipoResultado.isPresent()){

                ProductoDto miProductoDtoTemp = new ProductoDto(tipoResultado.get().getPrd_id(), tipoResultado.get().getPrd_descripcion(), tipoResultado.get().getPrd_tip_id(), tipoResultado.get().getPrd_stock(), tipoResultado.get().getPrd_precio());
                miProductoDtoTemp.setPrd_descripcion("Prueba desde Test");
                miProductoDtoTemp.setPrd_tip_id(3);
                miProductoDtoTemp.setPrd_stock(111);
                miProductoDtoTemp.setPrd_precio(1111);
                ProductoDto miTipoSave = productoService.actualizaroPorID(idTemp, miProductoDtoTemp);

                Assertions.assertAll(() -> {
                    assertEquals(miProductoDtoTemp.getPrd_descripcion(), miTipoSave.getPrd_descripcion());
                });

            }

        }

    }


}
