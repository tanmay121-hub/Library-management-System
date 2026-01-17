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

    @Override
    public String returnBook(int cardId, int bookId){
        Transaction transaction = transactionRepository.findByCard_CardIdAndBook_BookIdAndTransactionStatus(cardId,bookId,TransactionStatus.ISSUED);

        if (transaction== null){
            return "Transaction not found! Either book was not issued or card ID is wrong.";
        }

        Book book = transaction.getBook();
        Date issueDate = transaction.getIssueDate();
        Date returnDate = new Date();

        long timediff = Math.abs(returnDate.getTime() - issueDate.getTime());
        long daysPassed = java.util.concurrent.TimeUnit.DAYS.convert(timediff, java.util.concurrent.TimeUnit.MILLISECONDS);

        int loanDays = 15;  //free days
        double fineAmount = 0.0;

        if (daysPassed>loanDays){
            int extraDays = (int) (daysPassed - loanDays);
            fineAmount = extraDays * 5.0; // 5 Rupees per day
        }

        transaction.setAmount(fineAmount);
        transaction.setReturnDate(returnDate);
        transaction.setTransactionStatus(TransactionStatus.RETURNED);

        book.setIssued(false);
        transactionRepository.save(transaction);
        bookRepository.save(book);
        return "Book Returned Successfully. Fine Amount: " + fineAmount;
    }

}
