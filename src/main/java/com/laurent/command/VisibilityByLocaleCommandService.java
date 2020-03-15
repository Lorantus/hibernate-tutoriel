package com.laurent.command;

import com.laurent.model.Customer;
import com.laurent.model.VisibilityByLocale;
import com.laurent.service.VisibilityService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Service
public class VisibilityByLocaleCommandService {
    private final VisibilityService visibilityService;

    @Autowired
    public VisibilityByLocaleCommandService(VisibilityService visibilityService) {
        this.visibilityService = visibilityService;
    }

    public VisibiltyCommand create(Customer customer, Set<Locale> locales) {
        return new VisibilityByLocaleCommand(visibilityService, customer, locales);
    }

    @Getter
    public static class VisibilityByLocaleCommand implements VisibiltyCommand {
        private final VisibilityService visibilityService;

        private final Customer customer;
        private final Set<Locale> locales;

        public VisibilityByLocaleCommand(VisibilityService visibilityService, Customer customer, Set<Locale> locales) {
            this.visibilityService = visibilityService;
            this.customer = customer;
            this.locales = unmodifiableSet(locales);
        }

        @Override
        public void execute() {
            visibilityService.updateVisibility(customer, new VisibilityByLocale(locales));
        }
    }
}
