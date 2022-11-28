package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.dto.response.TipoProductoDto;
import com.example.CrudPB.entities.Producto;
import com.example.CrudPB.entities.TipoProducto;
import com.example.CrudPB.exceptions.RecordNotFoundException;
import com.example.CrudPB.repository.IProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    @Override
    public ProductoDto crearProducto(ProductoDto productoDto){
        Producto miTemporal = new Producto(productoDto.getPrd_id(), productoDto.getPrd_descripcion(), productoDto.getPrd_tip_id(), productoDto.getPrd_stock(), productoDto.getPrd_precio());

        try {
            Producto miTemp = productoRepository.save(miTemporal);
            ProductoDto miElementoCreado = new ProductoDto(miTemp.getPrd_id(), miTemp.getPrd_descripcion(), miTemp.getPrd_tip_id(), miTemp.getPrd_stock(), miTemp.getPrd_precio());
            return miElementoCreado ;
        }
    catch(DataAccessException e){
        ProductoDto miElementoCreado = new ProductoDto(0, "ERROR!!! No se pudo crear el producto, verifique si existe el Tipo ", 0, 0, 0);
        return miElementoCreado ;

        }
    }

    @Override
    public SuccessDto borrarProducto(Integer idProducto) {

        Optional<Producto> miTemp = productoRepository.findById(idProducto);
        if (miTemp.isPresent()){
            productoRepository.deleteById(idProducto);
            String respuesta = String.format("El producto con ID %s fue borrado exitosamente", idProducto);
            SuccessDto miRespuestaTemp = new SuccessDto(respuesta);
            return miRespuestaTemp ;
        }
        else {
            throw new RecordNotFoundException("Producto", "ID", idProducto);
        }
    }


  @Override
    public ProductoDto encontrarProductoPorID(Integer idProducto){
        Optional<Producto> miTemp = productoRepository.findById(idProducto);
        if (miTemp.isPresent()){
            ProductoDto miElementoCreado = new ProductoDto(miTemp.get().getPrd_id(), miTemp.get().getPrd_descripcion(), miTemp.get().getPrd_tip_id(), miTemp.get().getPrd_stock(), miTemp.get().getPrd_precio());
            return miElementoCreado ;
        }
        else {
            throw new RecordNotFoundException("Producto", "ID", idProducto);
        }
    }

    @Override
    public ProductoDto actualizaroPorID(Integer idProducto, ProductoDto ProductoDto){
        Producto miTemp = productoRepository.findById(idProducto).orElseThrow(
                ()-> new RecordNotFoundException("Producto", "ID", idProducto));

        miTemp.setPrd_descripcion(ProductoDto.getPrd_descripcion());
        miTemp.setPrd_precio(ProductoDto.getPrd_precio());
        miTemp.setPrd_tip_id(ProductoDto.getPrd_tip_id());
        miTemp.setPrd_precio(ProductoDto.getPrd_precio());

         try {
            productoRepository.save(miTemp);
            ProductoDto miRegistroActualizado = new ProductoDto(miTemp.getPrd_id(), miTemp.getPrd_descripcion(), miTemp.getPrd_tip_id(), miTemp.getPrd_stock(), miTemp.getPrd_precio());
            return miRegistroActualizado;
        }
        catch(DataAccessException e){
            ProductoDto miRegistroActualizado = new ProductoDto(0, "ERROR!!! No se pudo actualizar el producto, verifique si existe el Tipo ", 0, 0, 0);
            return miRegistroActualizado ;

        }





    }

}
