package com.saleshub.ms.servicesales.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    BAD_PARAMETERS("SCI-001", ErrorCategoryEnum.INVALID_REQUEST,
            "Parámetros ingresados no existen o son incorrectos"),
    PAYMENT_TYPE_ID_NOT_EXISTS("SCI-002", ErrorCategoryEnum.INVALID_REQUEST,
            "El paymentTypeId proporcionado no existe");

    private final String code;
    private final ErrorCategoryEnum category;
    private final String detail;
}
