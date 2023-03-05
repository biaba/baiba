package com.skujevska.baiba.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class AppExceptionHandler {

    /**
     * Thrown if entry doesn't comply with DB constraints
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<String> dbException(
            final ConstraintViolationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * TO DO - add specific exception handling methods for different use cases
     * Displays error message
     */
   @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<String> otherException(
            final Throwable ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
