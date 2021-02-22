package com.system.bibliotec.controller.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.system.bibliotec.exception.ResourceNotFoundException;

@Service
public class EndPointUtil implements Serializable {

    public ResponseEntity<?> returnObjectOrNotFound(Object object) {
        if (object == null) throw new ResourceNotFoundException("Recurso Solicitado Não encontrado");
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    public ResponseEntity<?> returnObjectOrNotFound(List<?> list) {
        if (list == null || list.isEmpty()) throw new ResourceNotFoundException("Recurso Solicitado Não encontrado");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
