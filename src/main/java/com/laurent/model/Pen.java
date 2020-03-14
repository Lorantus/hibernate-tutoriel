package com.laurent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("PEN")
public class Pen extends Product {
    private String color;
}