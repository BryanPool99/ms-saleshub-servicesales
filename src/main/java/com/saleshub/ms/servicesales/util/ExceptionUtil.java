package com.saleshub.ms.servicesales.util;

import com.saleshub.ms.servicesales.model.ErrorType;
import com.saleshub.ms.servicesales.model.ErrorTypeDetail;
import com.saleshub.ms.servicesales.util.constants.Constants;
import com.saleshub.ms.servicesales.util.enums.ErrorEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil extends RuntimeException {

    public static ErrorType buildGenesisException(ErrorEnum errorEnum) {
        return ErrorType.builder()
                .exceptionId(errorEnum.getCategory().getExceptionId())
                .exceptionText(Constants.CUSTOM_EXCEPTION_TEXT)
                .moreInfo(Constants.MORE_INFO)
                .httpStatus(errorEnum.getCategory().getHttpStatus())
                .exceptionDetails(List.of(
                        ErrorTypeDetail.builder()
                                .code(errorEnum.getCode())
                                .component(Constants.MICROSERVICE)
                                .description(errorEnum.getDetail())
                                .build()
                )).build();
    }

    public static <T> Mono<T> buildGenesisExceptionMono(ErrorEnum errorEnum) {
        ErrorType errorType = buildGenesisException(errorEnum);
        return Mono.error(new CustomApiException(errorType));
    }

    public static <T> Flux<T> buildGenesisExceptionFlux(ErrorEnum errorEnum) {
        ErrorType errorType = buildGenesisException(errorEnum);
        return Flux.error(new CustomApiException(errorType));
    }
}
