package com.laurent.command.factory;

import com.laurent.command.VisibilityByNameCommand;
import com.laurent.command.VisibilityType;
import com.laurent.command.VisibiltyCommand;
import com.laurent.service.VisibilityByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
class VisibilityCommandByNameFactory implements VisibilityCommandFactory {
    private final VisibilityByNameService visibilityByNameService;

    @Autowired
    public VisibilityCommandByNameFactory(VisibilityByNameService visibilityByNameService) {
        this.visibilityByNameService = visibilityByNameService;
    }

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.NAME;
    }

    @Override
    public VisibiltyCommand createVisibilityService(Set<String> associations) {
        return new VisibilityByNameCommand(visibilityByNameService, associations);
    }
}
