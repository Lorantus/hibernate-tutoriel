package com.laurent.service.visibility.factory;

import com.laurent.model.Visibility;
import com.laurent.model.VisibilityByLocale;
import com.laurent.model.VisibilityType;
import com.laurent.service.VisibilityStrategy;
import com.laurent.service.visibility.VisibilityByLocaleStrategy;
import org.springframework.stereotype.Component;

@Component
public class VisibilityByLocaleStrategyFactory implements VisibilityStrategyFactory {

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.LOCALE;
    }

    @Override
    public VisibilityStrategy createVisibilityStrategy(Visibility visibility) {
        return new VisibilityByLocaleStrategy(((VisibilityByLocale)visibility).getLocales());
    }
}
