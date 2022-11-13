package com.example.CrudPB.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controllers {

    @GetMapping("/listar_productos")
    public String seleccionarProductos(){
        return "todos los productos";
    }

    @GetMapping("/listar_tipoSProductos")
    public String seleccionarTiposProductos(){
        return "todos los productos";
    }
}
