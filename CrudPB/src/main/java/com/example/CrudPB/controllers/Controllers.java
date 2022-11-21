package com.example.CrudPB.controllers;

import com.example.CrudPB.dto.request.ProductoDto;
import com.example.CrudPB.dto.request.TipoProductoDto;
import com.example.CrudPB.service.IProductoService;
import com.example.CrudPB.service.ITipoProductoService;
import com.example.CrudPB.service.ProductoService;
import com.example.CrudPB.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/buscar_tipoSPorId")
    public String seleccionarTiposPorId(){
        return "todos los productos";
    }

    @PostMapping("/crear_productos")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(productoService.crearProducto(productoDto), HttpStatus.CREATED);
    }

}
