package com.example.CrudPB.service;

import com.example.CrudPB.dto.request.ProductoDto;

import java.util.List;

public interface IProductoService {

    public List<ProductoDto> listarProductos();
}
