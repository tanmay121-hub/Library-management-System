package com.learn.librarymanagement.service.implementation;

import com.learn.librarymanagement.dto.AuthorRequestDto;
import com.learn.librarymanagement.dto.AuthorResponseDto;
import com.learn.librarymanagement.model.Author;
import com.learn.librarymanagement.model.Book;
import com.learn.librarymanagement.repository.AuthorRepository;
import com.learn.librarymanagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    private AuthorResponseDto mapToResponse(Author author) {
        AuthorResponseDto response = new AuthorResponseDto();
        response.setId(author.getAuthId());
        response.setName(author.getName());
        response.setAge(author.getAge());
        response.setEmail(author.getEmail());

        // Extract Book Titles efficiently
        List<String> titles = new ArrayList<>();
        if (author.getBooks() != null) {
            for (Book book : author.getBooks()) {
                titles.add(book.getTitle());
            }
        }
        response.setBookTitles(titles);

        return response;
    }

    @Override
    public String addAuthor(AuthorRequestDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setAge(authorDto.getAge());
        author.setEmail(authorDto.getEmail());
        authorRepository.save(author);
        return "Author Added Successfully";
    }

    @Override
    public AuthorResponseDto findAuthorById(int id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) return null;
        return mapToResponse(author);
    }

    @Override
    public List<AuthorResponseDto> findAllAuthors() {
        List<Author> authors = authorRepository.findAllAuthorsWithBooks();
        List<AuthorResponseDto> responseList = new ArrayList<>();

        for (Author author : authors) {
            responseList.add(mapToResponse(author));
        }
        return responseList;
    }

}
