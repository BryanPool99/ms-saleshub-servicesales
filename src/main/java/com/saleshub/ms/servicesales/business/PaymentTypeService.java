package com.saleshub.ms.servicesales.business;

import com.saleshub.ms.servicesales.model.CreatePaymentRequest;
import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import com.saleshub.ms.servicesales.model.UpdatePaymentTypeRequest;
import reactor.core.publisher.Mono;

public interface PaymentTypeService {

    Mono<RetrievePaymentType> listPaymentType(String filter, Integer limit, Integer offset, String sort, String user);

    Mono<Void> createPaymentType(CreatePaymentRequest createPaymentRequest, String user);

    Mono<Void> updatePaymentType(Integer paymentTypeId, UpdatePaymentTypeRequest updatePaymentTypeRequest, String user);
}
