package com.laurent.command.factory;

import com.laurent.command.VisibilityByNoneCommandService;
import com.laurent.command.VisibiltyCommand;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
class VisibilityCommandByNoneFactory implements VisibilityCommandFactory {
    private final VisibilityByNoneCommandService visibilityByNoneCommandService;

    @Autowired
    public VisibilityCommandByNoneFactory(VisibilityByNoneCommandService visibilityByNoneCommandService) {
        this.visibilityByNoneCommandService = visibilityByNoneCommandService;
    }

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.NONE;
    }

    @Override
    public VisibiltyCommand createVisiblityCommand(Customer customer, Set<String> visibilities) {
        return visibilityByNoneCommandService.create(customer);
    }
}
