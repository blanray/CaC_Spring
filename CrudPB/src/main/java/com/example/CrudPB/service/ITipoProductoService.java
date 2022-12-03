package com.example.CrudPB.service;


import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.dto.response.TipoProductoDto;

import java.util.List;

public interface ITipoProductoService {

     List<TipoProductoDto> listarTipoProductos();

     TipoProductoDto crearTipoProducto(TipoProductoDto tipoProductoDto);

    TipoProductoDto encontrarTipoPorID(Integer idTipoProducto);

    TipoProductoDto actualizarTipoPorID(Integer idTipoProducto, TipoProductoDto tipoProductoDto);

     SuccessDto borrarTipoPorID(Integer idTipoProducto);

    SuccessDto borrarAllTipos();

}
