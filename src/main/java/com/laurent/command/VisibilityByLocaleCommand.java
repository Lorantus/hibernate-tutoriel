package com.laurent.command;

import com.laurent.model.Customer;
import com.laurent.service.VisibilityByLocaleService;
import lombok.Getter;

import java.util.Locale;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Getter
public class VisibilityByLocaleCommand implements VisibiltyCommand {
    private final VisibilityByLocaleService visibilityByLocaleService;
    private final Set<Locale> locales;

    public VisibilityByLocaleCommand(VisibilityByLocaleService visibilityByLocaleService, Set<Locale> locales) {
        this.visibilityByLocaleService = visibilityByLocaleService;
        this.locales = unmodifiableSet(locales);
    }

    @Override
    public void associer(Customer customer) {
        visibilityByLocaleService.associer(customer, locales);
    }
}
