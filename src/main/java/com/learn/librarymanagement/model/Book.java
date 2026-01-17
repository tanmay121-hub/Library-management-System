package com.learn.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.librarymanagement.enums.Genre;
import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    //bookId,title,genre , price, noofpages,author

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String title;
    private int price;
    private int noOfPages;
    private boolean isIssued = false; // Default is false (Available)

    @Enumerated(EnumType.STRING)
    Genre genre;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Author author;

    public Book() {
    }

    public Book(int bookId, String title, int price, int noOfPages, Genre genre, Author author) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.noOfPages = noOfPages;
        this.genre = genre;
        this.author = author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
