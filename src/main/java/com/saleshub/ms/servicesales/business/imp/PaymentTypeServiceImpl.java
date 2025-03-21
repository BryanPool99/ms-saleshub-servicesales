package com.saleshub.ms.servicesales.business.imp;

import com.saleshub.ms.servicesales.business.PaymentTypeService;
import com.saleshub.ms.servicesales.model.CreatePaymentRequest;
import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import com.saleshub.ms.servicesales.model.UpdatePaymentTypeRequest;
import com.saleshub.ms.servicesales.model.entity.PaymentTypeEntity;
import com.saleshub.ms.servicesales.repository.PaymentTypeRepository;
import com.saleshub.ms.servicesales.strategic.paymenttype.PaymentTypeStrategyFactory;
import com.saleshub.ms.servicesales.util.Util;
import com.saleshub.ms.servicesales.util.enums.ErrorEnum;
import com.saleshub.ms.servicesales.util.enums.paymenttype.PaymentTypeFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static com.saleshub.ms.servicesales.util.ExceptionUtil.buildGenesisExceptionMono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeStrategyFactory paymentTypeStrategyFactory;

    private final PaymentTypeRepository paymentTypeRepository;

    private final TransactionalOperator transactionalOperator;

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

    @Override
    public Mono<Void> createPaymentType(CreatePaymentRequest createPaymentRequest, String user) {
        log.info("Starts the createPaymentType for user: {}", user);
        return createPaymentTypeEntity(createPaymentRequest, user)
                .then()
                .as(transactionalOperator::transactional)
                .doOnSuccess(success -> log.info("PaymentType created successfully"))
                .doOnError(error -> log.error("Error during createPaymentType process", error));
    }

    private Mono<PaymentTypeEntity> createPaymentTypeEntity(CreatePaymentRequest createPaymentRequest, String user) {
        log.info("Starts the method createPaymentTypeEntity");
        var paymentType = PaymentTypeEntity.builder()
                .description(createPaymentRequest.getDescription())
                .status(createPaymentRequest.getStatus())
                .createdBy(user)
                .creationDate(LocalDateTime.now())
                .build();

        return paymentTypeRepository.save(paymentType)
                .doOnError(error -> log.error("Error in createPaymentTypeEntity: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of createPaymentTypeEntity"));
    }

    @Override
    public Mono<Void> updatePaymentType(
            Integer paymentTypeId, UpdatePaymentTypeRequest updatePaymentTypeRequest, String user) {
        log.info("Start the method updatePaymentType for paymentTypeId: {} and user: {}", paymentTypeId, user);
        return paymentTypeRepository.findByPaymentTypeId(paymentTypeId)
                .switchIfEmpty(Mono.defer(() -> buildGenesisExceptionMono(ErrorEnum.PAYMENT_TYPE_ID_NOT_EXISTS)))
                .flatMap(paymentTypeEntity ->
                        updatePaymentTypeEntity(paymentTypeEntity, updatePaymentTypeRequest, user))
                .then()
                .as(transactionalOperator::transactional)
                .doOnSuccess(success -> log.info("PaymentType updated successfully"))
                .doOnError(error -> log.error("Error during updatePaymentType process", error));
    }

    private Mono<PaymentTypeEntity> updatePaymentTypeEntity(
            PaymentTypeEntity paymentTypeEntity, UpdatePaymentTypeRequest updatePaymentTypeRequest, String user) {
        log.info("Starts the method updatePaymentTypeEntity");
        Optional.ofNullable(updatePaymentTypeRequest.getDescription())
                .ifPresent(paymentTypeEntity::setDescription);
        Optional.ofNullable(updatePaymentTypeRequest.getStatus())
                .ifPresent(paymentTypeEntity::setStatus);
        paymentTypeEntity.setUpdatedBy(user);
        paymentTypeEntity.setUpdateDate(LocalDateTime.now());

        return paymentTypeRepository.save(paymentTypeEntity)
                .doOnError(error -> log.error("Error in updatePaymentTypeEntity: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of updatePaymentTypeEntity"));
    }
}
