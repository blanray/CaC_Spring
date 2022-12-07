package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.TipoProductoDto;
import com.example.CrudPB.entities.TipoProducto;
import com.example.CrudPB.repository.IProductoRepository;
import com.example.CrudPB.repository.ITipoProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

@Test
void probarFindAllTipo(){

    List<TipoProducto> listaTemp = tipoProductoRepository.findAll();

    when(tipoProductoRepository.findAll()).thenReturn(listaTemp);
    List<TipoProductoDto> listaResultado = tipoProductoService.listarTipoProductos();

    Assertions.assertAll(()->{
        assertEquals(listaTemp.size(),listaResultado.size());
    });
}


}
