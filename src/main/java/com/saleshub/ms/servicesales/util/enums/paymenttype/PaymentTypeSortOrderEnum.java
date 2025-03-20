package com.saleshub.ms.servicesales.util.enums.paymenttype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeSortOrderEnum {

    ID_ASC("id,asc"),
    ID_DESC("id,desc"),
    DESCRIPTION_ASC("description,asc"),
    DESCRIPTION_DESC("description,desc"),
    STATUS_ASC("status,asc"),
    STATUS_DESC("status,desc");

    private final String value;
}
