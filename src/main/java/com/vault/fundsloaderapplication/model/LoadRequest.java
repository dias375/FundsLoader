package com.vault.fundsloaderapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadRequest {
    private long id;
    private long customer_id;
    private BigDecimal load_amount;
    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ssZ")
    private Date time;
}
