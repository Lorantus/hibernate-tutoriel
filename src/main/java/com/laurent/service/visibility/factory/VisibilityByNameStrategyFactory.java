package com.laurent.service.visibility.factory;

import com.laurent.model.Visibility;
import com.laurent.model.VisibilityByName;
import com.laurent.model.VisibilityType;
import com.laurent.service.VisibilityByNameStrategy;
import com.laurent.service.VisibilityStrategy;
import org.springframework.stereotype.Component;

@Component
public class VisibilityByNameStrategyFactory implements VisibilityStrategyFactory {

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.NAME;
    }

    @Override
    public VisibilityStrategy createVisibilityStrategy(Visibility visibility) {
        return new VisibilityByNameStrategy(((VisibilityByName)visibility).getNames());
    }
}
