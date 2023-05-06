package com.vault.fundsloaderapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawLoadRequest {
    private long id;
    private long customer_id;
    private String load_amount;
    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ssZ")
    private Date time;

    public BigDecimal getLoadAmountInBigDecimal() throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "$#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
    return (BigDecimal) decimalFormat.parse(load_amount);
    }
}
