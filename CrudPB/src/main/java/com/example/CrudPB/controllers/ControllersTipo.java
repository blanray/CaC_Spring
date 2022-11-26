package com.example.CrudPB.controllers;

import com.example.CrudPB.dto.request.ReqProductoIDDto;
import com.example.CrudPB.dto.request.ReqTipoIDDto;
import com.example.CrudPB.dto.response.TipoProductoDto;
import com.example.CrudPB.service.ITipoProductoService;
import com.example.CrudPB.service.TipoProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllersTipo {

    private ITipoProductoService tipoProductoService;

  public ControllersTipo(TipoProductoService tipoProductoService) {this.tipoProductoService = tipoProductoService;}

    @GetMapping("/listar_tiposProductos")
    public ResponseEntity<?> listarTipoProductos(){
        return new ResponseEntity<>(tipoProductoService.listarTipoProductos(), HttpStatus.OK);
    }

    @PostMapping("/crear_tiposProductos")
    public ResponseEntity<?> crearTipoProducto(@RequestBody TipoProductoDto tipoProductoDto){
        return new ResponseEntity<>(tipoProductoService.crearTipoProducto(tipoProductoDto), HttpStatus.CREATED);
    }

    @GetMapping("/buscar_tiposPorID/{id}")
    public ResponseEntity<?> encontrarTipoPorID(@PathVariable Integer id){
        ReqTipoIDDto reqTipoIDDto = new ReqTipoIDDto(id);
        return new ResponseEntity<>(tipoProductoService.encontrarTipoPorID(reqTipoIDDto.getId()), HttpStatus.OK);
    }

    @PostMapping("/actualizar_tipoPorID/{id}")
    public ResponseEntity<?> actualizarTipoPorID(@PathVariable Integer id, @RequestBody TipoProductoDto tipoProductoDto){
        ReqTipoIDDto reqTipoIDDto = new ReqTipoIDDto(id);
        return new ResponseEntity<>(tipoProductoService.actualizarTipoPorID(reqTipoIDDto.getId(), tipoProductoDto), HttpStatus.OK);
    }

    @PostMapping("/borrar_tipoProducto/{id}")
    public ResponseEntity<?> borrarTipoPorID(@PathVariable Integer id){
        ReqProductoIDDto reqProductoIDDto = new ReqProductoIDDto(id);
        return new ResponseEntity<>(tipoProductoService.borrarTipoPorID(reqProductoIDDto.getId()), HttpStatus.OK);
    }


}
