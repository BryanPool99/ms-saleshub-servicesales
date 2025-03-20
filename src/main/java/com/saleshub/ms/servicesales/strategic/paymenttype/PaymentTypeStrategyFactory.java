package com.saleshub.ms.servicesales.strategic.paymenttype;

import com.saleshub.ms.servicesales.strategic.paymenttype.strategies.GetByIdPaymentTypeStrategy;
import com.saleshub.ms.servicesales.strategic.paymenttype.strategies.ListPaymentTypeStrategy;
import com.saleshub.ms.servicesales.util.Util;
import com.saleshub.ms.servicesales.util.enums.paymenttype.PaymentTypeStrategyTypeEnum;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class PaymentTypeStrategyFactory {

    private final Map<PaymentTypeStrategyTypeEnum, PaymentTypeStrategy>
            strategies = new EnumMap<>(PaymentTypeStrategyTypeEnum.class);

    public PaymentTypeStrategyFactory(
            ListPaymentTypeStrategy listPaymentTypeStrategy,
            GetByIdPaymentTypeStrategy getByIdPaymentTypeStrategy) {
        strategies.put(PaymentTypeStrategyTypeEnum.LIST_PAYMENT_TYPE, listPaymentTypeStrategy);
        strategies.put(PaymentTypeStrategyTypeEnum.GET_BY_ID_PAYMENT_TYPE, getByIdPaymentTypeStrategy);
    }

    public PaymentTypeStrategy getStrategy(String retrieveType) {
        return strategies.get(Util.getPaymentTypeStrategic().get(retrieveType));
    }
}
