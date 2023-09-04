package com.capitoleconsulting.techproof.infrastructure.config.callback.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SizeInput {

    private Long id;
    private Long productId;
    private Boolean backSoon;
    private Boolean special;
}
