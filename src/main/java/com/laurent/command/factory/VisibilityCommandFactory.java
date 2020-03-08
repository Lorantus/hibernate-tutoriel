package com.laurent.command.factory;

import com.laurent.command.VisibiltyCommand;
import com.laurent.model.Customer;
import com.laurent.model.VisibilityType;

import java.util.Set;

interface VisibilityCommandFactory {
    VisibilityType getVisibilityType();
    VisibiltyCommand createVisiblityCommand(Customer customer, Set<String> associations);
}
