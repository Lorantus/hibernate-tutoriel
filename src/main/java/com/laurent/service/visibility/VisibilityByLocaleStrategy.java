package com.laurent.service.visibility;

import com.laurent.model.Customer;
import com.laurent.service.VisibilityStrategy;

import java.util.Locale;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class VisibilityByLocaleStrategy implements VisibilityStrategy {
    private final Set<Locale> locales;

    public VisibilityByLocaleStrategy(Set<Locale> locales) {
        this.locales = unmodifiableSet(locales);
    }

    @Override
    public boolean isVisible(Customer customer) {
        return locales.contains(customer.getLocale());
    }
}
