package com.example.CrudPB.entities;

import lombok.Getter;

@Getter
public enum RolE {
    EMPLEADO("EMPLEADO"), GERENTE("GERENTE"), ADMIN("ADMIN");

    String text;

    RolE(String text) {
        this.text = text;
    }

}
