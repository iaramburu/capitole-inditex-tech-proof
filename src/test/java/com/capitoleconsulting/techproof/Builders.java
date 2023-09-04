package com.capitoleconsulting.techproof;

import com.capitoleconsulting.techproof.domain.JsonTestObject;
import com.capitoleconsulting.techproof.domain.entity.Product;
import com.capitoleconsulting.techproof.domain.entity.Size;
import com.capitoleconsulting.techproof.domain.entity.Stock;

import java.math.BigDecimal;
import java.util.Arrays;

public class Builders {

    public static Product product1() {
        return Product.builder()
                .id(1L)
                .sequence(10)
                .sizeList(Arrays.asList(size11(), size12(), size13()))
                .build();
    }

    public static Product product2() {
        return Product.builder()
                .id(2L)
                .sequence(7)
                .sizeList(Arrays.asList(size21(), size22(), size23()))
                .build();
    }

    public static Product product3() {
        return Product.builder()
                .id(3L)
                .sequence(15)
                .sizeList(Arrays.asList(size31(), size32(), size33()))
                .build();
    }

    public static Product product4() {
        return Product.builder()
                .id(4L)
                .sequence(13)
                .sizeList(Arrays.asList(size41(), size42(), size43(), size44()))
                .build();
    }

    public static Product product5() {
        return Product.builder()
                .id(5L)
                .sequence(6)
                .sizeList(Arrays.asList(size51(), size52(), size53(), size54()))
                .build();
    }

    public static Product productWithoutSizes() {
        return Product.builder()
                .id(6L)
                .sequence(20)
                .build();
    }

    public static Size size11() {
        return Size.builder()
                .id(11L)
                .backSoon(true)
                .special(false)
                .stock(stock11())
                .build();
    }

    public static Size size12() {
        return Size.builder()
                .id(12L)
                .backSoon(false)
                .special(false)
                .stock(stock12())
                .build();
    }

    public static Size size13() {
        return Size.builder()
                .id(13L)
                .backSoon(true)
                .special(false)
                .stock(stock13())
                .build();
    }

    public static Size size21() {
        return Size.builder()
                .id(21L)
                .backSoon(false)
                .special(false)
                .build();
    }

    public static Size size22() {
        return Size.builder()
                .id(22L)
                .backSoon(false)
                .special(false)
                .stock(stock22())
                .build();
    }

    public static Size size23() {
        return Size.builder()
                .id(23L)
                .backSoon(true)
                .special(true)
                .build();
    }

    public static Size size31() {
        return Size.builder()
                .id(31L)
                .backSoon(true)
                .special(false)
                .stock(stock31())
                .build();
    }

    public static Size size32() {
        return Size.builder()
                .id(32L)
                .backSoon(true)
                .special(false)
                .stock(stock32())
                .build();
    }

    public static Size size33() {
        return Size.builder()
                .id(33L)
                .backSoon(false)
                .special(false)
                .stock(stock33())
                .build();
    }

    public static Size size41() {
        return Size.builder()
                .id(41L)
                .backSoon(false)
                .special(false)
                .stock(stock41())
                .build();
    }

    public static Size size42() {
        return Size.builder()
                .id(42L)
                .backSoon(false)
                .special(false)
                .stock(stock42())
                .build();
    }

    public static Size size43() {
        return Size.builder()
                .id(43L)
                .backSoon(false)
                .special(false)
                .stock(stock43())
                .build();
    }

    public static Size size44() {
        return Size.builder()
                .id(44L)
                .backSoon(true)
                .special(true)
                .stock(stock44())
                .build();
    }

    public static Size size51() {
        return Size.builder()
                .id(51L)
                .backSoon(true)
                .special(false)
                .stock(stock51())
                .build();
    }

    public static Size size52() {
        return Size.builder()
                .id(52L)
                .backSoon(false)
                .special(false)
                .stock(stock52())
                .build();
    }

    public static Size size53() {
        return Size.builder()
                .id(53L)
                .backSoon(false)
                .special(false)
                .stock(stock53())
                .build();
    }

    public static Size size54() {
        return Size.builder()
                .id(54L)
                .backSoon(true)
                .special(true)
                .stock(stock54())
                .build();
    }

    public static Stock stock11() {
        return Stock.builder()
                .quantity(0)
                .build();
    }

    public static Stock stock12() {
        return Stock.builder()
                .quantity(0)
                .build();
    }

    public static Stock stock13() {
        return Stock.builder()
                .quantity(0)
                .build();
    }

    public static Stock stock22() {
        return Stock.builder()
                .quantity(0)
                .build();
    }

    public static Stock stock31() {
        return Stock.builder()
                .quantity(10)
                .build();
    }

    public static Stock stock32() {
        return Stock.builder()
                .quantity(10)
                .build();
    }

    public static Stock stock33() {
        return Stock.builder()
                .quantity(10)
                .build();
    }

    public static Stock stock41() {
        return Stock.builder()
                .quantity(0)
                .build();
    }

    public static Stock stock42() {
        return Stock.builder()
                .quantity(0)
                .build();
    }

    public static Stock stock43() {
        return Stock.builder()
                .quantity(0)
                .build();
    }

    public static Stock stock44() {
        return Stock.builder()
                .quantity(10)
                .build();
    }

    public static Stock stock51() {
        return Stock.builder()
                .quantity(10)
                .build();
    }

    public static Stock stock52() {
        return Stock.builder()
                .quantity(10)
                .build();
    }

    public static Stock stock53() {
        return Stock.builder()
                .quantity(10)
                .build();
    }

    public static Stock stock54() {
        return Stock.builder()
                .quantity(10)
                .build();
    }

    public static JsonTestObject validJsonTestObject() {
        return JsonTestObject.builder()
                .integerValue(1)
                .longValue(2L)
                .doubleValue(3D)
                .floatValue(4F)
                .bigDecimalValue(BigDecimal.valueOf(5.10))
                .stringValue("String")
                .build();
    }
}
