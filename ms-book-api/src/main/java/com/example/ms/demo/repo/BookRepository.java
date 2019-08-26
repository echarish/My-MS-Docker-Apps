package com.example.ms.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ms.demo.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}