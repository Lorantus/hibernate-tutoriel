package com.laurent.command;

import com.laurent.model.Customer;
import com.laurent.service.VisibilityByNameService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Service
public class VisibilityByNameCommandService {
    private final VisibilityByNameService visibilityByNameService;

    @Autowired
    public VisibilityByNameCommandService(VisibilityByNameService visibilityByNameService) {
        this.visibilityByNameService = visibilityByNameService;
    }

    public VisibiltyCommand create(Customer customer, Set<String> names) {
        return new VisibilityByNameCommand(visibilityByNameService, customer, names);
    }

    @Getter
    public static class VisibilityByNameCommand implements VisibiltyCommand {
        private final VisibilityByNameService visibilityByNameService;

        private final Customer customer;
        private final Set<String> names;

        public VisibilityByNameCommand(VisibilityByNameService visibilityByNameService, Customer customer, Set<String> names) {
            this.visibilityByNameService = visibilityByNameService;
            this.customer = customer;
            this.names = unmodifiableSet(names);
        }

        @Override
        public void execute() {
            visibilityByNameService.associer(customer, names);
        }
    }
}
