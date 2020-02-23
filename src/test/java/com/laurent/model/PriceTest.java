package com.laurent.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceTest {

    @Test
    public void doitRetournerLeTotalPourUnPrixEtUnTaux() {
        Price price = new Price(new BigDecimal("100"));
        price.setTaux(new BigDecimal("10"));

        BigDecimal total = price.getTotal();

        assertThat(total).isEqualByComparingTo("110");
    }

    @Test
    public void doitRetournerLeTotalPourUnPrixEtUneReduction() {
        Price price = new Price(new BigDecimal("100"));
        price.setReduce(new BigDecimal("10"));

        BigDecimal total = price.getTotal();

        assertThat(total).isEqualByComparingTo("90");
    }

    @Test
    public void doitRetournerLeTotalPourUnPrixEtUneReductionEtUnTaux() {
        Price price = new Price(new BigDecimal("100"));
        price.setReduce(new BigDecimal("50"));
        price.setTaux(new BigDecimal("10"));

        BigDecimal total = price.getTotal();

        assertThat(total).isEqualByComparingTo("55");
    }
}