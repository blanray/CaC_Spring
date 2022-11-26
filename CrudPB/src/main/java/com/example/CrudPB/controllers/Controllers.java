package com.example.CrudPB.controllers;

import com.example.CrudPB.dto.request.ReqProductoIDDto;
import com.example.CrudPB.dto.request.ReqTipoIDDto;
import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.dto.response.TipoProductoDto;
import com.example.CrudPB.service.IProductoService;
import com.example.CrudPB.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controllers {

    private IProductoService productoService;
    public Controllers(ProductoService productoService) {this.productoService = productoService;}

@GetMapping("/listar_productos")
    public ResponseEntity<?> listarProductos(){
        return new ResponseEntity<>(productoService.listarProductos(), HttpStatus.OK);
    }

    @PostMapping("/crear_productos")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(productoService.crearProducto(productoDto), HttpStatus.CREATED);
    }

    @GetMapping("/buscar_productosID/{id}")
    public ResponseEntity<?> encontrarProductoPorID(@PathVariable Integer id){
        ReqProductoIDDto reqProductoIDDto = new ReqProductoIDDto(id);
        return new ResponseEntity<>(productoService.encontrarProductoPorID(reqProductoIDDto.getId()), HttpStatus.OK);
    }

    @PostMapping("/actualizar_PorID/{id}")
    public ResponseEntity<?> actualizaroPorID(@PathVariable Integer id, @RequestBody ProductoDto ProductoDto){
        ReqProductoIDDto reqProductoIDDto = new ReqProductoIDDto(id);
        return new ResponseEntity<>(productoService.actualizaroPorID(reqProductoIDDto.getId(), ProductoDto), HttpStatus.OK);
    }
    @PostMapping("/borrar_producto/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Integer id){
        ReqProductoIDDto reqProductoIDDto = new ReqProductoIDDto(id);
        return new ResponseEntity<>(productoService.borrarProducto(reqProductoIDDto.getId()), HttpStatus.OK);
    }

}
