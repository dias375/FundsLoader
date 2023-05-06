package com.vault.fundsloaderapplication.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawLoadRequest {
    private long id;
    @JsonAlias("customer_id")
    private long customerId;
    @JsonAlias("load_amount")
    String loadAmount;
    private LocalDateTime time;

    public BigDecimal getLoadAmountInBigDecimal() {
        String loadAmount = this.getLoadAmount().substring(1);
        return new BigDecimal(loadAmount);
    }
}
