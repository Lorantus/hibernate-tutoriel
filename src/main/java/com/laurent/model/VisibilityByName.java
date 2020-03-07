package com.laurent.model;

import com.laurent.command.VisibilityType;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Getter
@Entity
@DiscriminatorValue("NAME")
public class VisibilityByName extends Visibility {
    @ElementCollection
    private Set<String> names;

    public VisibilityByName(Set<String> names) {
        this.names = unmodifiableSet(names);
    }
    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.NAME;
    }

    @Override
    public boolean isVisible(Customer customer) {
        return names.contains(customer.getName());
    }

}
