package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TrabalhoExistenteException extends AgendadorException {


    public TrabalhoExistenteException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public TrabalhoExistenteException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public TrabalhoExistenteException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public TrabalhoExistenteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


}
