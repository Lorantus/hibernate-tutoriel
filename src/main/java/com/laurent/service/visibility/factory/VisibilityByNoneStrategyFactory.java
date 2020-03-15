package com.laurent.service.visibility.factory;

import com.laurent.model.Visibility;
import com.laurent.model.VisibilityType;
import com.laurent.service.VisibilityStrategy;
import com.laurent.service.visibility.VisibilityByNoneStrategy;
import org.springframework.stereotype.Component;

@Component
public class VisibilityByNoneStrategyFactory implements VisibilityStrategyFactory {

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.NONE;
    }

    @Override
    public VisibilityStrategy createVisibilityStrategy(Visibility visibility) {
        return new VisibilityByNoneStrategy();
    }
}
