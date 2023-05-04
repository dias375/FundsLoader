package com.vault.fundsloaderapplication.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOAD_REQUEST")
public class LoadRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "CUSTOMER_ID")
    private long customer_id;

    @Column(name = "LOAD_AMOUNT")
    private long load_amount;

    @Column(name = "TIME")
    private long time; //TODO Change to UTC time
}
