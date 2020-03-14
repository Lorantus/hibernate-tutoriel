package com.laurent.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Embeddable
public class Price {
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final BigDecimal HUNDRED_SQUARE = HUNDRED.multiply(HUNDRED);

    private BigDecimal price;
    private BigDecimal reduce = ZERO;
    private BigDecimal taux = ZERO;

    public Price(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return price.multiply(HUNDRED.subtract(reduce))
                .multiply(HUNDRED.add(taux))
                .divide(HUNDRED_SQUARE, HALF_UP);
    }
}
