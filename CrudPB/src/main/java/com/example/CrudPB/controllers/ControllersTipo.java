package com.example.CrudPB.controllers;

import com.example.CrudPB.service.ITipoProductoService;
import com.example.CrudPB.service.TipoProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllersTipo {

    private ITipoProductoService tipoProductoService;

  public ControllersTipo(TipoProductoService tipoProductoService) {this.tipoProductoService = tipoProductoService;}

    @GetMapping("/listar_tiposProductos")
    public ResponseEntity<?> listarTipoProductos(){
        return new ResponseEntity<>(tipoProductoService.listarTipoProductos(), HttpStatus.OK);
    }

    @PostMapping("/craar_tipoProducto")
    public String crearTipoProducto(){
        return "todos los productos";
    }


}
