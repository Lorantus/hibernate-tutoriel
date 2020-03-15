package com.laurent.service;

import com.laurent.model.Customer;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class VisibilityByNameStrategy implements VisibilityStrategy {
    private final Set<String> names;

    public VisibilityByNameStrategy(Set<String> names) {
        this.names = unmodifiableSet(names);
    }

    @Override
    public boolean isVisible(Customer customer) {
        return names.contains(customer.getLastName());
    }
}
