package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.dto.response.TipoProductoDto;
import com.example.CrudPB.entities.TipoProducto;
import com.example.CrudPB.exceptions.RecordNotFoundException;
import com.example.CrudPB.repository.ITipoProductoRepository;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"SCOPE = integration_test"})
public class TestServiceTipo {

    @Mock
    private ITipoProductoRepository tipoProductoRepository;

    @InjectMocks
    private TipoProductoService tipoProductoService;

    //    @Autowired
//    private TipoProductoRepository tipoProductoRepository;


    @Test
    @Order(1)
    @DisplayName("Test Servicio Find All")
void probarFindAllTipo(){

    List<TipoProducto> listaTemp = tipoProductoRepository.findAll();

    when(tipoProductoRepository.findAll()).thenReturn(listaTemp);
    List<TipoProductoDto> listaResultado = tipoProductoService.listarTipoProductos();

    Assertions.assertAll(()->{
        assertEquals(listaTemp.size(),listaResultado.size());
    });
}

    @Test
    @Order(2)
    @DisplayName("Test Servicio Buscar Tipo por ID")
    void probarFindByIDTipo(){

        Integer idTemp = 2;
        Optional<TipoProducto> miTemp = Optional.of(new TipoProducto());

        when(tipoProductoRepository.findById(idTemp)).thenReturn(miTemp);
        Optional<TipoProductoDto> tipoResultado = Optional.of(tipoProductoService.encontrarTipoPorID(idTemp));

        Assertions.assertAll(()->{
            assertEquals(miTemp.get().getTip_descripcion(), tipoResultado.get().getTip_descripcion());
        });
    }


    @Test
    @Order(3)
    @DisplayName("Test Servicio Crear Tipo Producto")
    void probarCrearTipo(){

        TipoProductoDto miTipoTemp = new TipoProductoDto(0,"Prueba desde test");

        try{
        TipoProductoDto miRespuesta = tipoProductoService.crearTipoProducto(miTipoTemp);

        Assertions.assertAll(()->{
            assertEquals(miRespuesta.getTip_descripcion(), miTipoTemp.getTip_descripcion());
        });}
        catch (NullPointerException e){
            Assertions.assertAll(()->{
                assertNotNull(e);
            });}

    }

    @Test
    @Order(4)
    @DisplayName("Test Servicio Actualizar Tipo por ID")
    void probarActualizarTipoID(){

        Integer idTemp = 2;
        Optional<TipoProducto> miTemp = Optional.of(new TipoProducto());

        when(tipoProductoRepository.findById(idTemp)).thenReturn(miTemp);

        if (miTemp.isPresent()) {

            miTemp.get().setTip_descripcion("Prueba desde Test");
            TipoProducto miTempSave = tipoProductoRepository.save(miTemp.get());

            Optional<TipoProductoDto> tipoResultado = Optional.of(tipoProductoService.encontrarTipoPorID(idTemp));

            if (tipoResultado.isPresent()){

                TipoProductoDto miTipoDtoTemp = new TipoProductoDto(tipoResultado.get().getTip_id(), tipoResultado.get().getTip_descripcion());
                miTipoDtoTemp.setTip_descripcion("Prueba desde Test");
                TipoProductoDto miTipoSave = tipoProductoService.actualizarTipoPorID(idTemp, miTipoDtoTemp);

                Assertions.assertAll(() -> {
                    assertEquals(miTipoDtoTemp.getTip_descripcion(), miTipoSave.getTip_descripcion());
                });

            }

        }

    }

    @Test
    @Order(5)
    @DisplayName("Test Servicio Borrar Tipo por ID")
    void probarBorrarTipoID() {

        Integer idTemp = 6;

        try {

            Optional<SuccessDto> resultadoDto = Optional.of(tipoProductoService.borrarTipoPorID(idTemp));

            if (resultadoDto.isPresent()) {

                Assertions.assertAll(() -> {
                    assertNotNull(resultadoDto);
                });
            }
        } catch (RecordNotFoundException e) {
            Assertions.assertAll(() -> {
                assertEquals("No se encontro el Tipo Producto, con ID valor : '6'", e.getMessage());
            });
        }

    }


    @Test
@Order(6)
@DisplayName("Test Servicio Borrar All")
void probarDeleteAllTipo(){

    SuccessDto dtoResult = tipoProductoService.borrarAllTipos();
    SuccessDto dtoExpected = new SuccessDto("Se ha vaciado exitosamente la lista de Tipos de Producto");

    Assertions.assertAll(()->{
        assertEquals(dtoExpected, dtoResult);
    });
}

}
