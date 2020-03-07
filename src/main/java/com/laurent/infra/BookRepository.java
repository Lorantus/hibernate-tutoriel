package com.laurent.infra;

import com.laurent.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
}