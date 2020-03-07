package com.laurent.infra;

import com.laurent.model.Pen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PenRepository extends CrudRepository<Pen, UUID> {
    List<Pen> findByName(String name);
    List<Pen> findByColor(String color);
}