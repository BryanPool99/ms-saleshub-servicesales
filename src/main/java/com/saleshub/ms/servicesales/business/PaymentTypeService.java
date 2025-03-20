package com.saleshub.ms.servicesales.business;

import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import reactor.core.publisher.Mono;

public interface PaymentTypeService {

    Mono<RetrievePaymentType> listPaymentType(String filter, Integer limit, Integer offset, String sort, String user);
}
