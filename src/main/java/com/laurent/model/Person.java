package com.laurent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
class Person {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
}