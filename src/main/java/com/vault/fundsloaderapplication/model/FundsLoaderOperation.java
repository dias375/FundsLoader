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
    @Column(name = "UUID")
    private UUID uuid = UUID.randomUUID();

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
}