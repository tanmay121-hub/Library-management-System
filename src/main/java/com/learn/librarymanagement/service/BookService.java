package com.learn.librarymanagement.service;

import com.learn.librarymanagement.dto.BookRequestDto;
import com.learn.librarymanagement.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    public String addBook(BookRequestDto bookDto);
    List<Book> getALl();
    Book getByID(int id);
    String updatePrice(int id, int price);
    String deleteByID(int id);
}
