package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.entities.Producto;
import com.example.CrudPB.exceptions.RecordNotFoundException;
import com.example.CrudPB.repository.IProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
          List<Producto> producto = productoRepository.findAll();
        if (producto.size()<1) {
        Producto temp = new Producto(0, "La lista está vacia", 0, 0, 0);
        producto.add(temp);
        }

        List<ProductoDto> productoDtos = producto.stream().map(prd -> {
            return new ProductoDto(prd.getPrd_id(), prd.getPrd_descripcion(), prd.getPrd_tip_id(), prd.getPrd_stock(), prd.getPrd_precio());
        }).collect(Collectors.toList());
        return productoDtos;
    }
    @Override
    public ProductoDto crearProducto(ProductoDto productoDto){
        Producto miTemporal = new Producto(productoDto.getPrd_id(), productoDto.getPrd_descripcion(), productoDto.getPrd_tip_id(), productoDto.getPrd_stock(), productoDto.getPrd_precio());
        ProductoDto miElementoCreado;

        try {
            Producto miTemp = productoRepository.save(miTemporal);
            miElementoCreado = new ProductoDto(miTemp.getPrd_id(), miTemp.getPrd_descripcion(), miTemp.getPrd_tip_id(), miTemp.getPrd_stock(), miTemp.getPrd_precio());
            return miElementoCreado ;
        }
    catch(DataAccessException e){
            miElementoCreado = new ProductoDto(0, "ERROR!!! No se pudo crear el producto, verifique si existe el Tipo ", 0, 0, 0);
        return miElementoCreado ;

        }
    }

    @Override
    public SuccessDto borrarProducto(Integer idProducto) {

        SuccessDto miRespuestaTemp;
        Optional<Producto> miTemp = productoRepository.findById(idProducto);
        if (miTemp.isPresent()){
            productoRepository.deleteById(idProducto);
            String respuesta = String.format("El producto con ID %s fue borrado exitosamente", idProducto);
            miRespuestaTemp = new SuccessDto(respuesta);
            return miRespuestaTemp ;
        }
        else {
            throw new RecordNotFoundException("Producto", "ID", idProducto);
        }
    }


  @Override
    public ProductoDto encontrarProductoPorID(Integer idProducto){
        Optional<Producto> miTemp = productoRepository.findById(idProducto);
      ProductoDto miElementoCreado;

        if (miTemp.isPresent()){
            miElementoCreado = new ProductoDto(miTemp.get().getPrd_id(), miTemp.get().getPrd_descripcion(), miTemp.get().getPrd_tip_id(), miTemp.get().getPrd_stock(), miTemp.get().getPrd_precio());
            return miElementoCreado ;
        }
        else {
            throw new RecordNotFoundException("Producto", "ID", idProducto);
        }
    }

    @Override
    public ProductoDto actualizaroPorID(Integer idProducto, ProductoDto ProductoDto){
        ProductoDto miRegistroActualizado;
        Producto miTemp = productoRepository.findById(idProducto).orElseThrow(
                ()-> new RecordNotFoundException("Producto", "ID", idProducto));

        miTemp.setPrd_descripcion(ProductoDto.getPrd_descripcion());
        miTemp.setPrd_precio(ProductoDto.getPrd_precio());
        miTemp.setPrd_tip_id(ProductoDto.getPrd_tip_id());
        miTemp.setPrd_precio(ProductoDto.getPrd_precio());

         try {
            productoRepository.save(miTemp);
            miRegistroActualizado = new ProductoDto(miTemp.getPrd_id(), miTemp.getPrd_descripcion(), miTemp.getPrd_tip_id(), miTemp.getPrd_stock(), miTemp.getPrd_precio());
            return miRegistroActualizado;
        }
        catch(DataAccessException e){
            miRegistroActualizado = new ProductoDto(0, "ERROR!!! No se pudo actualizar el producto, verifique si existe el Tipo ", 0, 0, 0);
            return miRegistroActualizado ;

        }
    }

    @Override
    public SuccessDto borrarAllProductos(){

        try{
        productoRepository.deleteAll();
            String respuesta = "Se ha vaciado exitosamente la lista de Productos";
            SuccessDto miRespuestaTemp = new SuccessDto(respuesta);
            return miRespuestaTemp;
        }
        catch (DataIntegrityViolationException e){
            String respuesta = "Se produjo un error vaciando la lista de Productos";
            SuccessDto miRespuestaTemp = new SuccessDto(respuesta);
            return miRespuestaTemp ;
        }
    }


    @Override
    public List<ProductoDto> topMayorStock(){
        List<Producto> producto = productoRepository.findAll();
        if (producto.size()<1) {
            Producto temp = new Producto(0, "La lista está vacia", 0, 0, 0);
            producto.add(temp);

            List<ProductoDto> productoDtos = producto.stream().map(prd -> {
                return new ProductoDto(prd.getPrd_id(), prd.getPrd_descripcion(), prd.getPrd_tip_id(), prd.getPrd_stock(), prd.getPrd_precio());
            }).collect(Collectors.toList());
            return productoDtos;

        }
        else{

            Comparator<Producto> ordenarStock = Comparator.comparing(Producto::getPrd_stock, Comparator.reverseOrder());
            Comparator<Producto> ordenarTipo = Comparator.comparing(Producto::getPrd_tip_id);
            List<Producto> productoTemp = producto.stream()
                    .sorted(ordenarStock)
                    .limit(5)
                    .collect(Collectors.toList());

            List<ProductoDto> productoDtos = productoTemp.stream()
                    .sorted(ordenarTipo)
                    .map(prd -> {
                        return new ProductoDto(prd.getPrd_id(), prd.getPrd_descripcion(), prd.getPrd_tip_id(), prd.getPrd_stock(), prd.getPrd_precio());
                    }).collect(Collectors.toList());
            return productoDtos;

         }

    }

    @Override
    public List<ProductoDto> precioPorTipo(Integer idTipoProducto){

        List<Producto> producto = productoRepository.findAll();

            Comparator<Producto> ordenarPrecio = Comparator.comparing(Producto::getPrd_precio, Comparator.reverseOrder());

            List<Producto> productoTemp = producto.stream()
                    .filter(p->(p.getPrd_tip_id()==idTipoProducto))
                    .collect(Collectors.toList());

            if (productoTemp.size() < 1){
                Producto temp = new Producto(0, "No hay productos para ese ID de Tipo", 0, 0, 0);
                productoTemp.add(temp);

                List<ProductoDto> productoDtos = productoTemp.stream().map(prd -> {
                    return new ProductoDto(prd.getPrd_id(), prd.getPrd_descripcion(), prd.getPrd_tip_id(), prd.getPrd_stock(), prd.getPrd_precio());
                }).collect(Collectors.toList());
                return productoDtos;
            }
            else{
                List<ProductoDto> productoDtos = productoTemp.stream()
                        .sorted(ordenarPrecio)
                        .map(prd -> {
                            return new ProductoDto(prd.getPrd_id(), prd.getPrd_descripcion(), prd.getPrd_tip_id(), prd.getPrd_stock(), prd.getPrd_precio());
                        }).collect(Collectors.toList());
                return productoDtos;
            }
    }
}
