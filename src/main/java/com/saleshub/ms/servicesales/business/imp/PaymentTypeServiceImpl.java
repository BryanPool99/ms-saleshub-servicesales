package com.saleshub.ms.servicesales.business.imp;

import com.saleshub.ms.servicesales.business.PaymentTypeService;
import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import com.saleshub.ms.servicesales.strategic.paymenttype.PaymentTypeStrategyFactory;
import com.saleshub.ms.servicesales.util.Util;
import com.saleshub.ms.servicesales.util.enums.paymenttype.PaymentTypeFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeStrategyFactory paymentTypeStrategyFactory;

    @Override
    public Mono<RetrievePaymentType> listPaymentType(
            String filter, Integer limit, Integer offset, String sort, String user) {
        log.info("Starts the listPaymentType for filter: {}, sort: {}", filter, sort);
        Map<String, String> values = Util.parseStringToMap(filter);
        log.info("Values: {}", values);
        String retrieveType = values.get(PaymentTypeFilterEnum.PAYMENT_TYPE_RETRIEVE_TYPE.getValue());
        return paymentTypeStrategyFactory.getStrategy(retrieveType)
                .retrievePaymentType(values, limit, offset, sort)
                .doOnError(error -> log.error("Error in listPaymentType: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of listPaymentType"));
    }
}
