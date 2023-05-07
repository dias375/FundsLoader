package com.vault.fundsloaderapplication.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoadRequest {
    private long id;
    @JsonAlias("customer_id")
    private long customerId;
    @JsonAlias("load_amount")
    private BigDecimal loadAmount;
    private LocalDateTime time;

    public static LoadRequest from(RawLoadRequest rawLoadRequest) {
        return LoadRequest.builder()
                .id(rawLoadRequest.getId())
                .customerId(rawLoadRequest.getCustomerId())
                .loadAmount(rawLoadRequest.getLoadAmountInBigDecimal())
                .time(rawLoadRequest.getTime())
                .build();
    }
}
