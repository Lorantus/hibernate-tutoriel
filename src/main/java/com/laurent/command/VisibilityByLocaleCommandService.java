package com.laurent.command;

import com.laurent.model.Customer;
import com.laurent.service.VisibilityByLocaleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Service
public class VisibilityByLocaleCommandService {
    private final VisibilityByLocaleService visibilityByLocaleService;

    @Autowired
    public VisibilityByLocaleCommandService(VisibilityByLocaleService visibilityByLocaleService) {
        this.visibilityByLocaleService = visibilityByLocaleService;
    }

    public VisibiltyCommand create(Customer customer, Set<Locale> locales) {
        return new VisibilityByLocaleCommand(visibilityByLocaleService, customer, locales);
    }

    @Getter
    public static class VisibilityByLocaleCommand implements VisibiltyCommand {
        private final VisibilityByLocaleService visibilityByLocaleService;

        private final Customer customer;
        private final Set<Locale> locales;

        public VisibilityByLocaleCommand(VisibilityByLocaleService visibilityByLocaleService, Customer customer, Set<Locale> locales) {
            this.visibilityByLocaleService = visibilityByLocaleService;
            this.customer = customer;
            this.locales = unmodifiableSet(locales);
        }

        @Override
        public void execute() {
            visibilityByLocaleService.associer(customer, locales);
        }
    }
}
