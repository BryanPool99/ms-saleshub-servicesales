package com.saleshub.ms.servicesales.business.imp;

import com.saleshub.ms.servicesales.business.PaymentTypeService;
import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Override
    public Mono<RetrievePaymentType> listPaymentType(
            String filter, Integer limit, Integer offset, String sort,
            String user) {
        return null;
    }
}
