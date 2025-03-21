package com.saleshub.ms.servicesales.expose;

import com.saleshub.ms.servicesales.api.SalesApi;
import com.saleshub.ms.servicesales.business.PaymentTypeService;
import com.saleshub.ms.servicesales.business.SalesService;
import com.saleshub.ms.servicesales.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/servicesales")
@RequiredArgsConstructor
public class ServicesalesController implements SalesApi {

    private final SalesService salesService;
    private final PaymentTypeService paymentTypeService;

    @Override
    public Mono<ResponseEntity<Void>> createSales(
            String unICAServiceId, String unICAApplication, String UNICA_PID, String unICAUser, String authorization,
            Mono<CreateSaleRequest> createSaleRequest, ServerWebExchange exchange) {
        return SalesApi.super.createSales(unICAServiceId, unICAApplication, UNICA_PID, unICAUser, authorization,
                createSaleRequest, exchange);
    }

    @Override
    public Mono<ResponseEntity<RetrieveSalesHistoryResponse>> retrieveHistorySales(
            String unICAServiceId, String unICAApplication, String UNICA_PID, String unICAUser, String authorization,
            String filter, String sort, Integer limit, Integer offset, ServerWebExchange exchange) {
        return SalesApi.super.retrieveHistorySales(unICAServiceId, unICAApplication, UNICA_PID, unICAUser,
                authorization, filter, sort, limit, offset, exchange);
    }

    @Override
    public Mono<ResponseEntity<RetrieveSalesDetailResponse>> retrieveSalesDetail(
            String unICAServiceId, String unICAApplication, String UNICA_PID, String unICAUser, String authorization,
            String filter, ServerWebExchange exchange) {
        return SalesApi.super.retrieveSalesDetail(unICAServiceId, unICAApplication, UNICA_PID, unICAUser,
                authorization, filter, exchange);
    }

    @Override
    public Mono<ResponseEntity<RetrievePaymentType>> retrievePaymentType(
            String unICAServiceId, String unICAApplication, String UNICA_PID, String unICAUser, String authorization,
            String filter, String sort, Integer limit, Integer offset, ServerWebExchange exchange) {
        return paymentTypeService.listPaymentType(filter, limit, offset, sort, unICAUser)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> createPaymentType(
            String unICAServiceId, String unICAApplication, String UNICA_PID, String unICAUser, String authorization,
            Mono<CreatePaymentRequest> createPaymentRequest, ServerWebExchange exchange) {
        return createPaymentRequest
                .flatMap(request -> paymentTypeService.createPaymentType(request, unICAUser))
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @Override
    public Mono<ResponseEntity<Void>> updatePaymentType(
            String unICAServiceId, String unICAApplication, String UNICA_PID, String unICAUser, Integer paymentTypeId,
            String authorization, Mono<UpdatePaymentTypeRequest> updatePaymentTypeRequest, ServerWebExchange exchange) {
        return updatePaymentTypeRequest
                .flatMap(request ->
                        paymentTypeService.updatePaymentType(paymentTypeId, request, unICAUser))
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
