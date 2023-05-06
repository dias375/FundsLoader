package com.vault.fundsloaderapplication.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String load_amount;

    @Column(name = "TIME")
    private String time;

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