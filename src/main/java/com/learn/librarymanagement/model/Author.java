package com.learn.librarymanagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authId;

    private String name;
    private int age;
    private String email;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();

    public Author(int authId, String name, int age, String email, List<Book> books) {
        this.authId = authId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.books = books;
    }

    public Author() {
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
