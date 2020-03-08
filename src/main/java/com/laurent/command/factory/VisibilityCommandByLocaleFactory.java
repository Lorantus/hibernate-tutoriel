package com.laurent.command.factory;

import com.laurent.command.VisibilityByLocaleCommand;
import com.laurent.command.VisibilityType;
import com.laurent.command.VisibiltyCommand;
import com.laurent.service.VisibilityByLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
class VisibilityCommandByLocaleFactory implements VisibilityCommandFactory {
    private final VisibilityByLocaleService visibilityByLocaleService;

    @Autowired
    public VisibilityCommandByLocaleFactory(VisibilityByLocaleService visibilityByLocaleService) {
        this.visibilityByLocaleService = visibilityByLocaleService;
    }

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.LOCALE;
    }

    @Override
    public VisibiltyCommand createVisibilityService(Set<String> associations) {
        Set<Locale> locales = associations.stream()
                .map(Locale::new)
                .collect(toSet());
        return new VisibilityByLocaleCommand(visibilityByLocaleService, locales);
    }
}
