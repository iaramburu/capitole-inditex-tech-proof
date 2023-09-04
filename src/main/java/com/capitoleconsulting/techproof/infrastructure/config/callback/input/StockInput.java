package com.capitoleconsulting.techproof.infrastructure.config.callback.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StockInput {

    private Long sizeId;
    private Integer quantity;
}
