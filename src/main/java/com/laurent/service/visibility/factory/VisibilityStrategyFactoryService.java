package com.laurent.service.visibility.factory;

import com.laurent.model.Visibility;
import com.laurent.model.VisibilityType;
import com.laurent.service.VisibilityStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class VisibilityStrategyFactoryService {
    private final Map<VisibilityType, VisibilityStrategyFactory> factories;

    public VisibilityStrategyFactoryService(List<VisibilityStrategyFactory> factories) {
        this.factories = factories.stream()
                .collect(toMap(VisibilityStrategyFactory::getVisibilityType, Function.identity()));
    }

    public VisibilityStrategy createVisibilityStrategyFactory(Visibility visibility) {
        return factories.get(visibility.getVisibilityType())
                .createVisibilityStrategy(visibility);
    }
}
