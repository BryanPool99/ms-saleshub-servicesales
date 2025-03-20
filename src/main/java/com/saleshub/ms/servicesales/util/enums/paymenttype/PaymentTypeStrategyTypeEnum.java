package com.saleshub.ms.servicesales.util.enums.paymenttype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeStrategyTypeEnum {

    LIST_PAYMENT_TYPE("LPT"),
    GET_BY_ID_PAYMENT_TYPE("GBIPT");

    private final String value;
}
