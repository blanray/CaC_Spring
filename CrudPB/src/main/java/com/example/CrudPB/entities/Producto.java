package com.example.CrudPB.entities;


import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prd_id;
    private String prd_descripcion;
    private int prd_tip_id;
    private int prd_stock;
    private double prd_precio;
}
