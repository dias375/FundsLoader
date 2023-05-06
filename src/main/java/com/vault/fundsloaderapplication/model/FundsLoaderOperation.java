package com.vault.fundsloaderapplication.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPERATIONS")
public class FundsLoaderOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UUID")
    private UUID uuid;

    @Column(name = "ID")
    private long id;

    @Column(name = "CUSTOMER_ID")
    private long customer_id;

    @Column(name = "LOAD_AMOUNT")
    private BigDecimal load_amount;

    @Column(name = "TIME")
    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ssZ")
    private Date time;

    @Column(name = "ACCEPTED")
    private boolean accepted;

    public void setVariables(LoadRequest loadRequest, LoadResponse loadResponse){
        this.id = loadRequest.getId();
        this.customer_id = loadRequest.getCustomer_id();
        this.load_amount = loadRequest.getLoad_amount();
        this.time = loadRequest.getTime();
        this.accepted = loadResponse.isAccepted();
    }
}