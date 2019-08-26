package com.example.ms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms.demo.domain.Book;
import com.example.ms.demo.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookservice;

    @GetMapping("/books")
    public List getAllBooks() {
        return bookservice.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long bookId){
        return bookservice.getBook(bookId);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookservice.addBook(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable String bookId, @RequestBody Book book) {
        return bookservice.updateBook(bookId, book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long bookId) {
        bookservice.deleteBook(bookId);
    }
}
