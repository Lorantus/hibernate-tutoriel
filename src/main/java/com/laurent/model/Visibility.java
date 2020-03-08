package com.laurent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VISIBILITY_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Visibility {
    @Id
    @Column(name = "CUSTOMER_ID")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public abstract boolean isVisible();

    public abstract VisibilityType getVisibilityType();
}
