package com.saleshub.ms.servicesales.mapper;

import com.saleshub.ms.servicesales.model.MetadataResponse;
import com.saleshub.ms.servicesales.model.PaymentTypeResponse;
import com.saleshub.ms.servicesales.model.RetrievePaymentType;
import com.saleshub.ms.servicesales.model.dto.PaymentTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {

    @Mapping(target = "paymentsType", source = "paymentsType")
    @Mapping(target = "metadata", expression = "java(toMetadataResponse(limit, offset, totalElements))")
    RetrievePaymentType toRetrievePaymentType(
            Stream<PaymentTypeDto> paymentsType, Integer limit, Integer offset, long totalElements);

    MetadataResponse toMetadataResponse(Integer limit, Integer offset, long totalElements);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", source = "state")
    PaymentTypeResponse toPaymentTypeResponse(PaymentTypeDto paymentTypeDto);
}
