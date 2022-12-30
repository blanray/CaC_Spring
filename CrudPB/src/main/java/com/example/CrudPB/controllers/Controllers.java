package com.example.CrudPB.controllers;

import com.example.CrudPB.dto.request.ReqProductoIDDto;
import com.example.CrudPB.dto.response.ProductoDto;
import com.example.CrudPB.service.IProductoService;
import com.example.CrudPB.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping(path = "/CrudPB")
@RestController
public class Controllers {

    private IProductoService productoService;
    public Controllers(ProductoService productoService) {this.productoService = productoService;}

@GetMapping("/listar/productos")
    public ResponseEntity<?> listarProductos(){
        return new ResponseEntity<>(productoService.listarProductos(), HttpStatus.OK);
    }

    @PostMapping("/crear/productos")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(productoService.crearProducto(productoDto), HttpStatus.CREATED);
    }

    @GetMapping("/buscar/productosID/{id}")
    public ResponseEntity<?> encontrarProductoPorID(@PathVariable Integer id){
        ReqProductoIDDto reqProductoIDDto = new ReqProductoIDDto(id);
        return new ResponseEntity<>(productoService.encontrarProductoPorID(reqProductoIDDto.getId()), HttpStatus.OK);
    }

    @PostMapping("/actualizar/PorID/{id}")
    public ResponseEntity<?> actualizaroPorID(@PathVariable Integer id, @Valid @RequestBody ProductoDto ProductoDto){
        ReqProductoIDDto reqProductoIDDto = new ReqProductoIDDto(id);
        return new ResponseEntity<>(productoService.actualizaroPorID(reqProductoIDDto.getId(), ProductoDto), HttpStatus.OK);
    }

    @PostMapping("/borrar/producto/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Integer id){
        ReqProductoIDDto reqProductoIDDto = new ReqProductoIDDto(id);
        return new ResponseEntity<>(productoService.borrarProducto(reqProductoIDDto.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/productos")
    public ResponseEntity<?> borrarAllProductos(){
         return new ResponseEntity<>(productoService.borrarAllProductos(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/listar/productos/top5")
    public ResponseEntity<?> topMayorStock(){
        return new ResponseEntity<>(productoService.topMayorStock(), HttpStatus.OK);
    }

    @GetMapping("/listar/productosTipo/{id}")
    public ResponseEntity<?> precioPorTipo(@PathVariable Integer id){
        ReqProductoIDDto reqProductoIDDto = new ReqProductoIDDto(id);
        return new ResponseEntity<>(productoService.precioPorTipo(reqProductoIDDto.getId()), HttpStatus.OK);
    }


}
