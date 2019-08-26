package com.example.ms.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms.demo.domain.Book;
import com.example.ms.demo.repo.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List getAllBooks() {
        List books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseGet(Book::new);
    }

    public Book addBook(Book whiskey) {
       return bookRepository.save(whiskey);
    }

    public Book updateBook(String id, Book whiskey) {
       return bookRepository.save(whiskey);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}