package com.laurent.command.factory;

import com.laurent.command.VisibilityByNameCommandService;
import com.laurent.command.VisibiltyCommand;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
class VisibilityCommandByNameFactory implements VisibilityCommandFactory {
    private final VisibilityByNameCommandService visibilityByNameCommandService;

    @Autowired
    public VisibilityCommandByNameFactory(VisibilityByNameCommandService visibilityByNameCommandService) {
        this.visibilityByNameCommandService = visibilityByNameCommandService;
    }

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.NAME;
    }

    @Override
    public VisibiltyCommand createVisiblityCommand(Customer customer, Set<String> associations) {
        return visibilityByNameCommandService.create(customer, associations);
    }
}
