package com.saleshub.ms.servicesales.business.imp;

import com.saleshub.ms.servicesales.business.SalesService;
import com.saleshub.ms.servicesales.model.CreateSaleRequest;
import com.saleshub.ms.servicesales.model.RetrieveSalesDetailResponse;
import com.saleshub.ms.servicesales.model.RetrieveSalesHistoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesServiceImp implements SalesService {

    @Override
    public Mono<RetrieveSalesHistoryResponse> listHistorySales(
            String filter, Integer limit, Integer offset, String sort, String user) {
        return null;
    }

    @Override
    public Mono<RetrieveSalesDetailResponse> listSalesDetail(String filter, String user) {
        return null;
    }

    @Override
    public Mono<Void> createSales(CreateSaleRequest createSaleRequest, String user) {
        return null;
    }
}
