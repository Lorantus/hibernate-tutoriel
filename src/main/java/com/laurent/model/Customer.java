package com.laurent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends Person {

    @Id
    @GeneratedValue
    private UUID id;

    private String lastName;
    private Locale locale = Locale.FRANCE;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VISIBILITY_ID")
    private Visibility visibility;

    @OneToMany(mappedBy = "customer")
    private Collection<Order> orders = new HashSet<>();

    public Customer(String firstName, String lastName) {
        setName(firstName);
        this.lastName = lastName;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
        visibility.setCustomer(this);
    }
}