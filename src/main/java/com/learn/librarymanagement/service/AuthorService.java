package com.learn.librarymanagement.service;

import com.learn.librarymanagement.dto.AuthorRequestDto;
import com.learn.librarymanagement.dto.AuthorResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    public String addAuthor(AuthorRequestDto authorDto);
    AuthorResponseDto findAuthorById(int id);
    List<AuthorResponseDto> findAllAuthors();
}
