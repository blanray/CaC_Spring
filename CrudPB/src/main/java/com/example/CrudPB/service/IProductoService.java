package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.SuccessDto;


import java.util.List;

public interface IProductoService {

    List<ProductoDto> listarProductos();

    ProductoDto crearProducto(ProductoDto ProductoDto);

     SuccessDto borrarProducto(Integer idProducto);

     ProductoDto  encontrarProductoPorID(Integer idProducto);

     ProductoDto actualizaroPorID(Integer idProducto, ProductoDto ProductoDto);

}
