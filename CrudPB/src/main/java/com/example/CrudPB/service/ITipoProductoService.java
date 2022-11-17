package com.example.CrudPB.service;


import com.example.CrudPB.dto.request.TipoProductoDto;
import com.example.CrudPB.entities.TipoProducto;

import java.util.List;

public interface ITipoProductoService {

    public List<TipoProductoDto> listarTipoProductos();

}
