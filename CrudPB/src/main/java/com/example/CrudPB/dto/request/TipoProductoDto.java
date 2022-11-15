package com.example.CrudPB.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoProductoDto {

    @NotEmpty
    private int tip_id;

    @Size(min=5, max=35, message= "La descripcion del tipo de producto debe tener entre 5 y 35 caracteres")
    private String tip_descripcion;
}
