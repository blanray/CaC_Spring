package com.example.CrudPB.controllers;

import com.example.CrudPB.service.IProductoService;
import com.example.CrudPB.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controllers {

    private IProductoService productoService;

    public Controllers(ProductoService productoService){
        this.productoService= productoService;
    }

    @GetMapping("/listar_productos")
    public ResponseEntity<?> listarProductos(){
        return new ResponseEntity<>(productoService.listarProductos(), HttpStatus.OK);
    }

    @GetMapping("/listar_tipoSProductos")
    public String seleccionarTiposProductos(){
        return "todos los productos";
    }

    @GetMapping("/buscar_tipoSPorId")
    public String seleccionarTiposPorId(){
        return "todos los productos";
    }

    @PostMapping("/craar_producto")
    public String crearProducto(){
        return "todos los productos";
    }

    @PostMapping("/craar_tipoProducto")
    public String crearTipoProducto(){
        return "todos los productos";
    }

}
