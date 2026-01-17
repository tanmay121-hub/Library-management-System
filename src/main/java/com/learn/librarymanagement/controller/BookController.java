package com.learn.librarymanagement.controller;

import com.learn.librarymanagement.dto.BookRequestDto;
import com.learn.librarymanagement.model.Book;
import com.learn.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<String> addBook (BookRequestDto bookdto){
        String response = bookService.addBook(bookdto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/All")
    public ResponseEntity<List<Book>> getAll(){
        try{
            return new ResponseEntity<>(bookService.getALl(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        try {
            return new ResponseEntity<>(bookService.getByID(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/updatePrice")
    public ResponseEntity<String> updateBook(@RequestParam int id, @RequestParam int price){
        try {
            bookService.updatePrice(id,price);
            return new ResponseEntity<>("Price Updated Successfully",HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByID(int id){
        try {
            bookService.deleteByID(id);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
