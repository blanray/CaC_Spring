package com.example.CrudPB.service;


import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.dto.response.TipoProductoDto;

import java.util.List;

public interface ITipoProductoService {

    public List<TipoProductoDto> listarTipoProductos();

    public TipoProductoDto crearTipoProducto(TipoProductoDto tipoProductoDto);

    public TipoProductoDto encontrarTipoPorID(Integer idTipoProducto);

    public TipoProductoDto actualizarTipoPorID(Integer idTipoProducto, TipoProductoDto tipoProductoDto);

    public SuccessDto borrarTipoPorID(Integer idTipoProducto);


}
