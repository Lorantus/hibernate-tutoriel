package com.laurent.model;

import com.laurent.command.VisibilityType;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Locale;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Getter
@Entity
@DiscriminatorValue("LOCALE")
public class VisibilityByLocale extends Visibility {
    @ElementCollection
    private Set<Locale> locales;

    public VisibilityByLocale(Set<Locale> locales) {
        this.locales = unmodifiableSet(locales);
    }

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.LOCALE;
    }

    @Override
    public boolean isVisible(Customer customer) {
        return locales.contains(customer.getLocale());
    }
}
