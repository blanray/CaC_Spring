package com.example.CrudPB.service;


import com.example.CrudPB.dto.request.TipoProductoDto;
import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.entities.TipoProducto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITipoProductoService {

    public List<TipoProductoDto> listarTipoProductos();

    public TipoProductoDto crearTipoProducto(TipoProductoDto tipoProductoDto);


}
