package com.example.CrudPB.service;

import com.example.CrudPB.dto.request.ProductoDto;
import com.example.CrudPB.dto.request.TipoProductoDto;

import java.util.List;

public interface IProductoService {

    public List<ProductoDto> listarProductos();

    public ProductoDto crearProducto(ProductoDto ProductoDto);

}
