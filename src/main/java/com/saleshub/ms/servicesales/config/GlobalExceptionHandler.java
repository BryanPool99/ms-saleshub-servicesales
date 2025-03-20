package com.saleshub.ms.servicesales.config;

import com.saleshub.ms.servicesales.model.ErrorType;
import com.saleshub.ms.servicesales.model.ErrorTypeDetail;
import com.saleshub.ms.servicesales.util.CustomApiException;
import com.saleshub.ms.servicesales.util.constants.Constants;
import com.saleshub.ms.servicesales.util.enums.ErrorCategoryEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<ErrorType> handleCustomApiException(CustomApiException ex) {
        ErrorType errorType = ex.getErrorType();
        HttpStatus status = HttpStatus.valueOf(errorType.getHttpStatus());
        return new ResponseEntity<>(errorType, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorType> handleGenericException(Exception ex) {
        ErrorType errorType = buildDefaultError(ex);
        return new ResponseEntity<>(errorType, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ErrorType buildDefaultError(Exception exception) {
        return ErrorType.builder()
                .exceptionId(ErrorCategoryEnum.EXTERNAL_ERROR.getExceptionId())
                .exceptionText(Constants.EXCEPTION_TEXT)
                .moreInfo(Constants.MORE_INFO)
                .httpStatus(ErrorCategoryEnum.EXTERNAL_ERROR.getHttpStatus())
                .exceptionDetails(List.of(
                        ErrorTypeDetail.builder()
                                .code(Constants.EXCEPTION_CODE)
                                .component(Constants.MICROSERVICE)
                                .description(exception.getMessage())
                                .build()
                )).build();
    }
}
