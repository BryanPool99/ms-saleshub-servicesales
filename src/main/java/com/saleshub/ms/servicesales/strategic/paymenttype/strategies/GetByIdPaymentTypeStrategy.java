package com.saleshub.ms.servicesales.strategic.paymenttype.strategies;

import com.saleshub.ms.servicesales.mapper.PaymentTypeMapper;
import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import com.saleshub.ms.servicesales.repository.PaymentTypeRepository;
import com.saleshub.ms.servicesales.strategic.paymenttype.PaymentTypeStrategy;
import com.saleshub.ms.servicesales.util.enums.paymenttype.PaymentTypeFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetByIdPaymentTypeStrategy implements PaymentTypeStrategy {

    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;

    @Override
    public Mono<RetrievePaymentType> retrievePaymentType(
            Map<String, String> values, Integer limit, Integer offset,
            String sort) {
        Integer paymentTypeId = Integer.parseInt(values.get(PaymentTypeFilterEnum.PAYMENT_TYPE_ID.getValue()));
        log.info("Start the getByIdPaymentTypeStrategy for paymentTypeId: {}", paymentTypeId);
        return paymentTypeRepository.findPaymentTypeById(paymentTypeId)
                .map(paymentTypeMapper::toPaymentTypeResponse)
                .map(paymentTypeResponse ->
                        RetrievePaymentType.builder().paymentsType(List.of(paymentTypeResponse)).build())
                .doOnError(error -> log.error("Error in getByIdPaymentTypeStrategy: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of getByIdPaymentTypeStrategy"));
    }
}
