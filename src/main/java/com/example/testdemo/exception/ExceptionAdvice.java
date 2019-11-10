package com.example.testdemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    public ResponseEntity<Void> notFoundException(NotFoundException e){
        logger.error("Exception occurred: {} : {}", e.getClass(), e.getMessage());
        return ResponseEntity.notFound().build();
    }

}
