package com.vault.fundsloaderapplication.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPERATIONS")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UUID")
    private UUID uuid;

    @Column(name = "ID")
    private long id;

    @Column(name = "CUSTOMER_ID")
    @JsonAlias("customer_id")
    private long customerId;

    @Column(name = "LOAD_AMOUNT")
    @JsonAlias("load_amount")
    private BigDecimal loadAmount;

    @Column(name = "TIME")
    private LocalDateTime time;

    @Column(name = "ACCEPTED")
    private boolean accepted;

    public void setVariables(LoadRequest loadRequest, LoadResponse loadResponse) {
        this.id = loadRequest.getId();
        this.customerId = loadRequest.getCustomerId();
        this.loadAmount = loadRequest.getLoadAmount();
        this.time = loadRequest.getTime();
        this.accepted = loadResponse.isAccepted();
    }
}