package com.saleshub.ms.servicesales.util;

import com.saleshub.ms.servicesales.model.ErrorType;
import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

  private final ErrorType errorType;

  public CustomApiException(ErrorType errorType) {
    super(errorType.getExceptionText());
    this.errorType = errorType;
  }
}
