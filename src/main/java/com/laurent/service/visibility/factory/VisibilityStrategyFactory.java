package com.laurent.service.visibility.factory;

import com.laurent.model.Visibility;
import com.laurent.model.VisibilityType;
import com.laurent.service.VisibilityStrategy;

public interface VisibilityStrategyFactory {
    VisibilityType getVisibilityType();
    VisibilityStrategy createVisibilityStrategy(Visibility visibility);
}
