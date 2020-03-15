package com.laurent.command.factory;

import com.laurent.command.VisibilityByLocaleCommandService;
import com.laurent.command.VisibiltyCommand;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
class VisibilityCommandByLocaleFactory implements VisibilityCommandFactory {
    private final VisibilityByLocaleCommandService visibilityByLocaleCommandService;

    @Autowired
    public VisibilityCommandByLocaleFactory(VisibilityByLocaleCommandService visibilityByLocaleCommandService) {
        this.visibilityByLocaleCommandService = visibilityByLocaleCommandService;
    }

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.LOCALE;
    }

    @Override
    public VisibiltyCommand createVisiblityCommand(Customer customer, Set<String> visibilities) {
        if(visibilities == null || visibilities.isEmpty()) {
            throw new IllegalArgumentException("Empty visibilities is forbidden");
        }
        Set<Locale> locales = visibilities.stream()
                .map(Locale::new)
                .collect(toSet());
        return visibilityByLocaleCommandService.create(customer, locales);
    }
}
