package com.saleshub.ms.servicesales.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentTypeQueryConstants {

    public static final String PAYMENT_TYPE_QUERY_LIST = """
            SELECT
                pt.payment_type_id AS id,
                pt.description AS description,
                pt.aud_est_cod_in AS state
            FROM
                payment_type pt
            WHERE
                (:description IS NULL OR TRIM(UPPER(pt.description)) LIKE '%' || TRIM(UPPER(:description)) || '%')
            """;

    public static final String PAYMENT_TYPE_QUERY_GET_BY_ID = """
            SELECT
                pt.payment_type_id AS id,
                pt.description AS description,
                pt.aud_est_cod_in AS state
            FROM
                payment_type pt
            WHERE
                pt.payment_type_id = :paymentTypeId
            """;

    public static final String PAYMENT_TYPE_QUERY_COUNT = """
            SELECT
                COUNT(1)
            FROM
                payment_type pt
            WHERE
                (:description IS NULL OR TRIM(UPPER(pt.description)) LIKE '%' || TRIM(UPPER(:description)) || '%')
            """;
}
