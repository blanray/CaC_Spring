package com.example.CrudPB.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private int prd_id;
    private String prd_descripcion;
    private int prd_tip_id;
    private int prd_stock;
    private double prd_precio;
}
