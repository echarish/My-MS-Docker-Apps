package com.example.ms.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title = "";
    private String author = "";
    private String type = "";
    private double price = 0;
    private int numOfPages = 0;
    private String language = "";
    private String isbn13 = "";


    public Book() {
    }

    public Book(Long id, String title, String author, String type, double price, int numOfPages, String language, String isbn13) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.price = price;
        this.numOfPages = numOfPages;
        this.language = language;
        this.isbn13 = isbn13;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public double getPrice() { return price; }

    public int getNumOfPages() {
        return numOfPages;
    }

    public String getLanguage() {
        return language;
    }

    public String getIsbn13() {
        return isbn13;
    }
}