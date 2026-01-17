package com.learn.librarymanagement.service.implementation;

import com.learn.librarymanagement.dto.BookRequestDto;
import com.learn.librarymanagement.model.Author;
import com.learn.librarymanagement.model.Book;
import com.learn.librarymanagement.repository.AuthorRepository;
import com.learn.librarymanagement.repository.BookRepository;
import com.learn.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImp implements BookService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;


    @Override
    public String addBook(BookRequestDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElse(null);
        if (author == null) {
            return "Author not found! Cannot add book.";
        }

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setNoOfPages(bookDto.getNoOfPages());
        book.setGenre(bookDto.getGenre());

        book.setAuthor(author);

        author.getBooks().add(book);

        authorRepository.save(author);

        return "Book Added Successfully";
    }

    @Override
    public List<Book> getALl() {
        return bookRepository.findAll();
    }

    @Override
    public Book getByID(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public String updatePrice(int id,int price) {
        Book updated = bookRepository.findById(id).orElse(null);
        if (updated==null){
            return "failed to update";
        }
        updated.setPrice(price);
        bookRepository.save(updated);
        return "Updated Successfully";
    }

    @Override
    public String deleteByID(int id) {
        bookRepository.deleteById(id);
        return "Deleted Successfully";
    }

}

