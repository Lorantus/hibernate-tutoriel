package com.laurent.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Customer extends Person {

    @Id
    @GeneratedValue
    private UUID id;

    private String lastName;

    @OneToMany(mappedBy = "customer")
    private Collection<Order> orders = new HashSet<>();

    public Customer(String firstName, String lastName) {
        setName(firstName);
        this.lastName = lastName;
    }
}