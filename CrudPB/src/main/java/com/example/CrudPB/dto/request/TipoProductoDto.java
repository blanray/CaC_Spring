package com.example.CrudPB.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoProductoDto {

    @NotEmpty
    private int tip_id;

    @Size(min=5, max=35, message= "La descripcion del tipo de producto debe tener entre 5 y 35 caracteres")
    private String tip_descripcion;
}
