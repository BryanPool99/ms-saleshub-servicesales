package com.saleshub.ms.servicesales.util.enums.paymenttype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeFilterEnum {

    PAYMENT_TYPE_FILTER("description"),
    PAYMENT_TYPE_ID("paymentTypeId"),
    PAYMENT_TYPE_RETRIEVE_TYPE("retrieveType");

    private final String value;
}
