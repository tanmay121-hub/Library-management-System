package com.learn.librarymanagement.controller;

import com.learn.librarymanagement.dto.AuthorRequestDto;
import com.learn.librarymanagement.dto.AuthorResponseDto;
import com.learn.librarymanagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorRequestDto authorDto) {
        String result = authorService.addAuthor(authorDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<AuthorResponseDto> findAuthor(@RequestParam("id") int id) {
        AuthorResponseDto response = authorService.findAuthorById(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorResponseDto>> findAll() {
        return new ResponseEntity<>(authorService.findAllAuthors(), HttpStatus.OK);
    }
}
