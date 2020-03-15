package com.laurent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity
@DiscriminatorValue("NONE")
public class VisibilityByNone extends Visibility {

    @Override
    public VisibilityType getVisibilityType() {
        return VisibilityType.NONE;
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
