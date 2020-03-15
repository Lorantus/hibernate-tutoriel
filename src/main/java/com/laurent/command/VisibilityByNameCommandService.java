package com.laurent.command;

import com.laurent.model.Customer;
import com.laurent.model.VisibilityByName;
import com.laurent.service.VisibilityService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Service
public class VisibilityByNameCommandService {
    private final VisibilityService visibilityService;

    @Autowired
    public VisibilityByNameCommandService(VisibilityService visibilityService) {
        this.visibilityService = visibilityService;
    }

    public VisibiltyCommand create(Customer customer, Set<String> names) {
        return new VisibilityByNameCommand(visibilityService, customer, names);
    }

    @Getter
    public static class VisibilityByNameCommand implements VisibiltyCommand {
        private final VisibilityService visibilityService;

        private final Customer customer;
        private final Set<String> names;

        public VisibilityByNameCommand(VisibilityService visibilityService, Customer customer, Set<String> names) {
            this.visibilityService = visibilityService;
            this.customer = customer;
            this.names = unmodifiableSet(names);
        }

        @Override
        public void execute() {
            visibilityService.updateVisibility(customer, new VisibilityByName(names));
        }
    }
}
