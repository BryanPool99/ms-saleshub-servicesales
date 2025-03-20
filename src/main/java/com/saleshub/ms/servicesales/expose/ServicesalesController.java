package com.saleshub.ms.servicesales.expose;

import com.saleshub.ms.servicesales.api.SalesApi;
import com.saleshub.ms.servicesales.business.PaymentTypeService;
import com.saleshub.ms.servicesales.business.SalesService;
import com.saleshub.ms.servicesales.model.CreateSaleRequest;
import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import com.saleshub.ms.servicesales.model.RetrieveSalesDetailResponse;
import com.saleshub.ms.servicesales.model.RetrieveSalesHistoryResponse;
import lombok.RequiredArgsConstructor;
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
    public Mono<ResponseEntity<RetrievePaymentType>> retrievePaymentType(
            String unICAServiceId, String unICAApplication, String UNICA_PID, String unICAUser, String authorization,
            String filter, String sort, Integer limit, Integer offset, ServerWebExchange exchange) {
        return SalesApi.super.retrievePaymentType(unICAServiceId, unICAApplication, UNICA_PID, unICAUser,
                authorization, filter,sort, limit, offset, exchange);
    }

    @Override
    public Mono<ResponseEntity<RetrieveSalesDetailResponse>> retrieveSalesDetail(
            String unICAServiceId, String unICAApplication, String UNICA_PID, String unICAUser, String authorization,
            String filter, ServerWebExchange exchange) {
        return SalesApi.super.retrieveSalesDetail(unICAServiceId, unICAApplication, UNICA_PID, unICAUser,
                authorization, filter, exchange);
    }
}
