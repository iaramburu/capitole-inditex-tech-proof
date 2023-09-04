package com.capitoleconsulting.techproof.infrastructure.config.callback.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductInput {

    private Long id;
    private Integer sequence;
}
