package com.learn.librarymanagement.repository;

import com.learn.librarymanagement.enums.TransactionStatus;
import com.learn.librarymanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    // Find the specific transaction where status is ISSUED
    Transaction findByCard_CardIdAndBook_BookIdAndTransactionStatus(
            int cardId,
            int bookId,
            TransactionStatus status
    );
}
