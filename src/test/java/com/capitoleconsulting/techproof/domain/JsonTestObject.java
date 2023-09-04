package com.capitoleconsulting.techproof.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JsonTestObject {

    private Integer integerValue;
    private Long longValue;
    private Double doubleValue;
    private Float floatValue;
    private BigDecimal bigDecimalValue;
    private String stringValue;
}
