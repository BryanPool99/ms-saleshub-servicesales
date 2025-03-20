package com.saleshub.ms.servicesales.model.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_type")
@Getter
@Setter
@Builder
public class PaymentTypeEntity {

    @Id
    @Column("payment_type_id")
    private Integer paymentTypeId;

    @Column("description")
    private String description;

    @Column("aud_usu_alt_ds")
    private String createdBy;

    @Column("aud_fec_alt_ff")
    private LocalDateTime creationDate;

    @Column("aud_est_cod_in")
    private Integer status;

    @Column("aud_usu_mod_ds")
    private String updatedBy;

    @Column("aud_fec_mod_ff")
    private LocalDateTime updateDate;
}
