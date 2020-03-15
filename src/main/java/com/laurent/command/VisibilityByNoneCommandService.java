package com.laurent.command;

import com.laurent.model.Customer;
import com.laurent.model.VisibilityByNone;
import com.laurent.service.VisibilityService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisibilityByNoneCommandService {
    private final VisibilityService visibilityService;

    @Autowired
    public VisibilityByNoneCommandService(VisibilityService visibilityService) {
        this.visibilityService = visibilityService;
    }

    public VisibiltyCommand create(Customer customer) {
        return new VisibilityByNoneCommand(visibilityService, customer);
    }

    @Getter
    public static class VisibilityByNoneCommand implements VisibiltyCommand {
        private final VisibilityService visibilityService;

        private final Customer customer;

        public VisibilityByNoneCommand(VisibilityService visibilityService, Customer customer) {
            this.visibilityService = visibilityService;
            this.customer = customer;
        }

        @Override
        public void execute() {
            visibilityService.updateVisibility(customer, new VisibilityByNone());
        }
    }
}
