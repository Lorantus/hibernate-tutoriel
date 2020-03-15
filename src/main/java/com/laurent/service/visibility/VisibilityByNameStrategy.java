package com.laurent.service.visibility;

import com.laurent.model.Customer;
import com.laurent.service.VisibilityStrategy;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class VisibilityByNameStrategy implements VisibilityStrategy {
    private final Set<String> names;

    public VisibilityByNameStrategy(Set<String> names) {
        this.names = unmodifiableSet(names);
    }

    @Override
    public boolean isVisible(Customer customer) {
        return names.contains(customer.getName());
    }
}
