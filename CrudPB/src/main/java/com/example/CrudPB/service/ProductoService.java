package com.example.CrudPB.service;

import com.example.CrudPB.dto.request.ProductoDto;
import com.example.CrudPB.dto.request.TipoProductoDto;
import com.example.CrudPB.entities.Producto;
import com.example.CrudPB.entities.TipoProducto;
import com.example.CrudPB.repository.IProductoRepository;
import com.example.CrudPB.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService{

    private IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDto> listarProductos(){
        ObjectMapper mapper = new ObjectMapper();
        List<Producto> producto = productoRepository.findAll();
        List<ProductoDto> productoDtos = producto.stream().map( prd -> {
            return new ProductoDto(prd.getPrd_id(),prd.getPrd_descripcion(), prd.getPrd_tip_id(), prd.getPrd_stock(), prd.getPrd_precio());
        }).collect(Collectors.toList());

        return productoDtos;
    }

    public ProductoDto crearProducto(ProductoDto productoDto){
        Producto miTemporal = new Producto(productoDto.getPrd_id(), productoDto.getPrd_descripcion(), productoDto.getPrd_tip_id(), productoDto.getPrd_stock(), productoDto.getPrd_precio());
        Producto miTemp = productoRepository.save(miTemporal);
        ProductoDto miElementoCreado = new ProductoDto(miTemp.getPrd_id(), miTemp.getPrd_descripcion(), miTemp.getPrd_tip_id(),miTemp.getPrd_stock(), miTemp.getPrd_precio());
        return miElementoCreado ;
    }

}
