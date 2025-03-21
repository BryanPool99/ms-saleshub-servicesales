package com.saleshub.ms.servicesales.repository;

import com.saleshub.ms.servicesales.model.dto.PaymentTypeDto;
import com.saleshub.ms.servicesales.model.entity.PaymentTypeEntity;
import com.saleshub.ms.servicesales.util.constants.PaymentTypeQueryConstants;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentTypeRepository extends R2dbcRepository<PaymentTypeEntity, Integer> {

    @Query(value = PaymentTypeQueryConstants.PAYMENT_TYPE_QUERY_COUNT)
    Mono<Long> countPaymentTypeList(String description);

    @Query(value = PaymentTypeQueryConstants.PAYMENT_TYPE_QUERY_LIST + " ORDER BY id ASC LIMIT :limit OFFSET :offset")
    Flux<PaymentTypeDto> findPaymentTypeListIdAsc(String description, Integer limit, Integer offset);

    @Query(value = PaymentTypeQueryConstants.PAYMENT_TYPE_QUERY_LIST + " ORDER BY id DESC LIMIT :limit OFFSET :offset")
    Flux<PaymentTypeDto> findPaymentTypeListIdDesc(String description, Integer limit, Integer offset);

    @Query(value = PaymentTypeQueryConstants.PAYMENT_TYPE_QUERY_LIST +
            " ORDER BY description ASC LIMIT :limit OFFSET :offset")
    Flux<PaymentTypeDto> findPaymentTypeListDescriptionAsc(String description, Integer limit, Integer offset);

    @Query(value = PaymentTypeQueryConstants.PAYMENT_TYPE_QUERY_LIST +
            " ORDER BY description DESC LIMIT :limit OFFSET :offset")
    Flux<PaymentTypeDto> findPaymentTypeListDescriptionDesc(String description, Integer limit, Integer offset);

    @Query(value = PaymentTypeQueryConstants.PAYMENT_TYPE_QUERY_LIST +
            " ORDER BY status ASC LIMIT :limit OFFSET :offset")
    Flux<PaymentTypeDto> findPaymentTypeListStatusAsc(String description, Integer limit, Integer offset);

    @Query(value = PaymentTypeQueryConstants.PAYMENT_TYPE_QUERY_LIST +
            " ORDER BY status DESC LIMIT :limit OFFSET :offset")
    Flux<PaymentTypeDto> findPaymentTypeListStatusDesc(String description, Integer limit, Integer offset);

    @Query(value = PaymentTypeQueryConstants.PAYMENT_TYPE_QUERY_GET_BY_ID)
    Mono<PaymentTypeDto> findPaymentTypeById(Integer paymentTypeId);

    Mono<PaymentTypeEntity> findByPaymentTypeId(Integer paymentTypeId);
}
