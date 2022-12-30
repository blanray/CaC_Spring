package com.example.CrudPB.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Usuario y/o contraseña incorrecto/s");
    }

}
