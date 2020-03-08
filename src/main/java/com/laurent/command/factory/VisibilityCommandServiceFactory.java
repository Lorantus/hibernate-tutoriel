package com.laurent.command.factory;

import com.laurent.command.VisibilityType;
import com.laurent.command.VisibiltyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
public class VisibilityCommandServiceFactory {

    private final Map<VisibilityType, VisibilityCommandFactory> visibilityCommandServiceByType;

    @Autowired
    public VisibilityCommandServiceFactory(List<VisibilityCommandFactory> visibilityCommandFactories) {
        this.visibilityCommandServiceByType = visibilityCommandFactories.stream()
            .collect(toMap(VisibilityCommandFactory::getVisibilityType, Function.identity()));
    }

    public VisibiltyCommand createVisibilityCommand(VisibilityType visibilityType, Set<String> associations) {
        return visibilityCommandServiceByType.get(visibilityType)
                .createVisibilityService(associations);
    }
}
