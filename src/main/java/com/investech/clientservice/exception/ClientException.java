package com.investech.clientservice.exception;

import org.springframework.http.HttpStatus;

public class ClientException extends RuntimeException{

  public ClientException(String message) {
    super(message);
  }

  public ClientException(String message, Throwable cause) {
      super(message, cause);
    }

  public ClientException(Throwable cause) {
    super(cause);
  }

  public ClientException(HttpStatus httpStatus, String message) {

  }
}

