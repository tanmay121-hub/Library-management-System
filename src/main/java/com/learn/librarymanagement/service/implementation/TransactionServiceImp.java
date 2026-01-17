package com.learn.librarymanagement.service.implementation;

import com.learn.librarymanagement.enums.Status;
import com.learn.librarymanagement.enums.TransactionStatus;
import com.learn.librarymanagement.model.Book;
import com.learn.librarymanagement.model.Card;
import com.learn.librarymanagement.model.Transaction;
import com.learn.librarymanagement.repository.BookRepository;
import com.learn.librarymanagement.repository.CardRepository;
import com.learn.librarymanagement.repository.TransactionRepository;
import com.learn.librarymanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public String issueBook(int cardId, int bookId) {
        Book book = bookRepository.findById(cardId).orElse(null);

        if (book == null) {
            return "Book not found!";
        }
        if (book.isIssued()) {
            return "Book is already issued to someone else!";
        }

        Card card = cardRepository.findById(cardId).orElse(null);
        if (card == null) {
            return "Card not found!";
        }

        if (card.getCardstatus() != Status.ACTIVATED) {
            return "Card is not active! Please contact admin.";
        }

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        transaction.setIssueDate(new Date());
        transaction.setAmount(0.0);

        //updating book status
        book.setIssued(true);
        transactionRepository.save(transaction);
        bookRepository.save(book);
        return "Book Issued Successfully! Transaction ID: " + transaction.getTransactionId();
    }

    @Override
    public Transaction findById(int id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
}
