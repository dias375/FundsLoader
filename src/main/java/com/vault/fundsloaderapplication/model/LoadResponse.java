package com.vault.fundsloaderapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadResponse {
    private long id;
    private long customer_id;
    private boolean accepted;
}
