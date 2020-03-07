package com.laurent.command;

import com.laurent.service.VisibilityByLocaleService;
import com.laurent.service.VisibilityByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

@Component
public class VisibilityFactory {
    private final VisibilityByLocaleService visibilityByLocaleService;
    private final VisibilityByNameService visibilityByNameService;

    @Autowired
    public VisibilityFactory(VisibilityByLocaleService visibilityByLocaleService, VisibilityByNameService visibilityByNameService) {
        this.visibilityByLocaleService = visibilityByLocaleService;
        this.visibilityByNameService = visibilityByNameService;
    }

    public VisibiltyCommand createVisibilityCommand(Type type, Set<String> associations) {
        switch(type){
            case NAME:
                return new VisibilityByNameCommand(visibilityByNameService, associations);

            case LOCALE:
                Set<Locale> locales = associations.stream()
                        .map(Locale::new)
                        .collect(toSet());
                return new VisibilityByLocaleCommand(visibilityByLocaleService, locales);

            default:
                throw new IllegalArgumentException(format("Le type %s n'exite pas", type));
        }
    }

    public enum Type {
        LOCALE,
        NAME
    }
}
