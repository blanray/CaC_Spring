package com.example.CrudPB.dto.request;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class ReqTipoIDDto {
    @PositiveOrZero(message= "El ID no puede ser negativo")
    private Integer id;
}
