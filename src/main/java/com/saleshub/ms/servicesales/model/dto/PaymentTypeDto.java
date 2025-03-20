package com.saleshub.ms.servicesales.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaymentTypeDto {

    private Integer id;
    private String description;
    private Integer state;
}
