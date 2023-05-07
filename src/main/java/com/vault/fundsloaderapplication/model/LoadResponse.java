package com.vault.fundsloaderapplication.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoadResponse {
    private long id;
    @JsonAlias("customer_id")
    private long customerId;
    private boolean accepted;
}
