package com.laurent.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Locale;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    public boolean isVisible() {
        return locales.contains(getCustomer().getLocale());
    }
}
