package com.laurent.model;

import com.laurent.command.VisibilityType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="VISIBILITY_TYPE",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Visibility {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_id_fk"))
    private Customer customer;

    public abstract boolean isVisible(Customer customer);

    public abstract VisibilityType getVisibilityType();
}
