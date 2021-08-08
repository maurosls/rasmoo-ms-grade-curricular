package com.rasmoo.cliente.escola.gradecurricular.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MateriaException extends RuntimeException {

    private final long serialVersionUID = -1065507067878058239L;

    private final HttpStatus httpStatus;

    public MateriaException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
