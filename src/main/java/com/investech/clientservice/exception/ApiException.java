package com.investech.clientservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException {
  private HttpStatus httpStatus;
  private String message;

  public ApiException(HttpStatus httpStatus, String message) {
    this.httpStatus = httpStatus;
    this.message = message;
  }
}
