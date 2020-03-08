package com.laurent.command.factory;

import com.laurent.command.VisibilityType;
import com.laurent.command.VisibiltyCommand;

import java.util.Set;

interface VisibilityCommandFactory {
    VisibilityType getVisibilityType();
    VisibiltyCommand createVisibilityService(Set<String> associations);
}
