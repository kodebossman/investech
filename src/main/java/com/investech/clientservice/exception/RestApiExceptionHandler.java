package com.investech.clientservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler {

@ExceptionHandler(ClientException.class)
  public ResponseEntity<Object> handle(ClientException exception){

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ApiException  apiException = new ApiException(
      httpStatus,exception.getMessage());
    return new ResponseEntity<>(apiException,httpStatus);
  }
}
