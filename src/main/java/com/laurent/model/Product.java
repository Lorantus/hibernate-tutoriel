package com.laurent.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type",
        discriminatorType = DiscriminatorType.STRING)
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Embedded
    private Price price;

    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, BigDecimal price) {
        this(name, new Price(price));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}