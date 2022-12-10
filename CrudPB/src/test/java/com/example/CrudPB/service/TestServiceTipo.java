package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.dto.response.TipoProductoDto;
import com.example.CrudPB.entities.TipoProducto;
import com.example.CrudPB.repository.ITipoProductoRepository;
import com.example.CrudPB.repository.TipoProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestServiceTipo {

    @Mock
    private ITipoProductoRepository tipoProductoRepository;

    @InjectMocks
    private TipoProductoService tipoProductoService;
//    @Autowired
//    private TipoProductoRepository tipoProductoRepository;

    @Test
void probarFindAllTipo(){

    List<TipoProducto> listaTemp = tipoProductoRepository.findAll();

    when(tipoProductoRepository.findAll()).thenReturn(listaTemp);
    List<TipoProductoDto> listaResultado = tipoProductoService.listarTipoProductos();

    Assertions.assertAll(()->{
        assertEquals(listaTemp.size(),listaResultado.size());
    });
}

@Test
void probarDeleteAllTipo(){

    SuccessDto dtoResult = tipoProductoService.borrarAllTipos();
    SuccessDto dtoExpected = new SuccessDto("Se ha vaciado exitosamente la lista de Tipos de Producto");

    Assertions.assertAll(()->{
        assertEquals(dtoExpected, dtoResult);
    });
}


}
