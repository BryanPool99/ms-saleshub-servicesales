package com.saleshub.ms.servicesales.strategic.paymenttype;

import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface PaymentTypeStrategy {

    Mono<RetrievePaymentType> retrievePaymentType(
            Map<String, String> values, Integer limit, Integer offset,
            String sort);
}
