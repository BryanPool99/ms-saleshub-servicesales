package com.saleshub.ms.servicesales.business;

import com.saleshub.ms.servicesales.model.CreateSaleRequest;
import com.saleshub.ms.servicesales.model.RetrieveSalesDetailResponse;
import com.saleshub.ms.servicesales.model.RetrieveSalesHistoryResponse;
import reactor.core.publisher.Mono;

public interface SalesService {

    Mono<RetrieveSalesHistoryResponse> listHistorySales(
            String filter, Integer limit, Integer offset, String sort, String user);

    Mono<RetrieveSalesDetailResponse> listSalesDetail(String filter, String user);

    Mono<Void> createSales(CreateSaleRequest createSaleRequest,String user);
}
