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

    public void setVariables(long id, long customer_id, String load_amount, String time, boolean accepted){
        this.id = id;
        this.customer_id = customer_id;
        this.load_amount = load_amount;
        this.time = time;
        this.accepted = accepted;
    }
}