package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.dto.response.TipoProductoDto;

import java.util.List;

public interface IProductoService {

    public List<ProductoDto> listarProductos();

    public ProductoDto crearProducto(ProductoDto ProductoDto);

    public SuccessDto borrarProducto(Integer idProducto);

    public ProductoDto  encontrarProductoPorID(Integer idProducto);

    public ProductoDto actualizaroPorID(Integer idProducto, ProductoDto ProductoDto);

}
