package com.example.CrudPB.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Getter
@Setter
@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException{

        private String objetoNoEncontrado;
        private String campo;
        private Object valor;

        public RecordNotFoundException(String objetoNoEncontrado, String campo, Object valor){
              super(String.format("No se encontro el %s, con %s valor : '%s'", objetoNoEncontrado, campo, valor));
              this.objetoNoEncontrado = objetoNoEncontrado;
              this.campo = campo;
              this.valor = valor;
        }

}


