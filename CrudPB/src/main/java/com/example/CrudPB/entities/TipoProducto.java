package com.example.CrudPB.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TipoProducto {
    private int tip_id;
    private String tip_descripcion;
}
