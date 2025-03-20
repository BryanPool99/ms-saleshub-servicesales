package com.saleshub.ms.servicesales.strategic.paymenttype.strategies;

import com.saleshub.ms.servicesales.mapper.PaymentTypeMapper;
import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import com.saleshub.ms.servicesales.model.dto.PaymentTypeDto;
import com.saleshub.ms.servicesales.repository.PaymentTypeRepository;
import com.saleshub.ms.servicesales.strategic.paymenttype.PaymentTypeStrategy;
import com.saleshub.ms.servicesales.util.enums.paymenttype.PaymentTypeFilterEnum;
import com.saleshub.ms.servicesales.util.enums.paymenttype.PaymentTypeSortOrderEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListPaymentTypeStrategy implements PaymentTypeStrategy {

    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;

    @Override
    public Mono<RetrievePaymentType> retrievePaymentType(
            Map<String, String> values, Integer limit, Integer offset,
            String sort) {
        String description = values.get(PaymentTypeFilterEnum.PAYMENT_TYPE_FILTER.getValue());
        log.info("Start the listPaymentTypeStrategy for description : {} and sort: {}", description, sort);
        return Mono.zip(listPaymentTypeDB(description, limit, offset, sort).collectList(),
                        paymentTypeRepository.countPaymentTypeList(description))
                .map(tuple2 -> paymentTypeMapper.toRetrievePaymentType(tuple2.getT1().stream(),
                        limit, offset, tuple2.getT2()))
                .doOnError(error -> log.error("Error in listPaymentTypeStrategy: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of listPaymentTypeStrategy"));
    }

    private Flux<PaymentTypeDto> listPaymentTypeDB(String description, Integer limit, Integer offset, String sort) {
        if (sort.isBlank() || sort == null) {
            return paymentTypeRepository.findPaymentTypeListStatusAsc(description, limit, offset);
        }
        Map<String, Function<String, Flux<PaymentTypeDto>>> queries = Map.ofEntries(
                Map.entry(PaymentTypeSortOrderEnum.ID_ASC.getValue(), field ->
                        paymentTypeRepository.findPaymentTypeListIdAsc(description, limit, offset)),
                Map.entry(PaymentTypeSortOrderEnum.ID_DESC.getValue(), field ->
                        paymentTypeRepository.findPaymentTypeListIdDesc(description, limit, offset)),
                Map.entry(PaymentTypeSortOrderEnum.DESCRIPTION_ASC.getValue(), field ->
                        paymentTypeRepository.findPaymentTypeListDescriptionAsc(description, limit, offset)),
                Map.entry(PaymentTypeSortOrderEnum.DESCRIPTION_DESC.getValue(), field ->
                        paymentTypeRepository.findPaymentTypeListDescriptionDesc(description, limit, offset)),
                Map.entry(PaymentTypeSortOrderEnum.STATUS_ASC.getValue(), field ->
                        paymentTypeRepository.findPaymentTypeListStatusAsc(description, limit, offset)),
                Map.entry(PaymentTypeSortOrderEnum.STATUS_DESC.getValue(), field ->
                        paymentTypeRepository.findPaymentTypeListStatusDesc(description, limit, offset))
        );
        return queries.getOrDefault(sort, field -> Flux.defer(() ->
                Flux.error(new RuntimeException("Bad parameters")))).apply(sort);
    }
}
