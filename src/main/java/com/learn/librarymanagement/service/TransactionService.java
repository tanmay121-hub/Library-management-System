package com.learn.librarymanagement.service;

import com.learn.librarymanagement.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TransactionService {
    public String issueBook(int cardId, int bookId);
    Transaction findById(int id);
    List<Transaction> getAll();
    public String returnBook(int cardId, int bookId);
}
