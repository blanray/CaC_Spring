package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.TipoProductoDto;

import java.util.List;

public interface IProductoService {

    public List<ProductoDto> listarProductos();

    public ProductoDto crearProducto(ProductoDto ProductoDto);

    public void borrarProducto(Integer id);

    public ProductoDto  encontrarProductoPorID(Integer idProducto);
}
