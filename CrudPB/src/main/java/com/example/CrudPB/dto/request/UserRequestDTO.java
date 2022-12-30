package com.example.CrudPB.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class UserRequestDTO {
    @Size(min=1, max=35, message= "El username no puede estar vacio ni superar los 35 caracteres")
    private String userName;

    @Size(min=1, max=35, message= "El password no puede estar vacio ni superar los 35 caracteres")
    private String password;
}
