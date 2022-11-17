package com.example.CrudPB.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoDto {

    @NotEmpty
    private int prd_id;

    @Size(min=5, max=35, message= "La descripcion del producto debe tener entre 5 y 35 caracteres")
    private String prd_descripcion;

    @NotEmpty
    private int prd_tip_id;

    @PositiveOrZero(message= "El stock no puede ser negativo")
    private int prd_stock;

    @PositiveOrZero(message= "El precio no puede ser negativo")
    private double prd_precio;
}
