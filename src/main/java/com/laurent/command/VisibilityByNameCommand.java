package com.laurent.command;

import com.laurent.model.Customer;
import com.laurent.service.VisibilityByNameService;
import lombok.Getter;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Getter
public class VisibilityByNameCommand implements VisibiltyCommand {
    private final VisibilityByNameService visibilityByNameService;
    private final Set<String> names;

    public VisibilityByNameCommand(VisibilityByNameService visibilityByNameService, Set<String> names) {
        this.visibilityByNameService = visibilityByNameService;
        this.names = unmodifiableSet(names);
    }

    @Override
    public void associer(Customer customer) {
        visibilityByNameService.associer(customer, names);
    }
}
