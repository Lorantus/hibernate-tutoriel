package com.laurent.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@Entity
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
}
