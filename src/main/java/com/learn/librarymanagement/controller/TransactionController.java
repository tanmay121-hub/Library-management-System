package com.learn.librarymanagement.controller;


import com.learn.librarymanagement.dto.StudentResponse;
import com.learn.librarymanagement.model.Transaction;
import com.learn.librarymanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    //issue
    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestParam("card_Id") int cardId, @RequestParam("book_Id") int bookId){
        transactionService.issueBook(cardId, bookId);
        return new ResponseEntity<>("Successfully Issued Book",HttpStatus.CREATED);
    }

    @GetMapping("searchById/{id}")
    public ResponseEntity<Transaction> getById(int id){
        Transaction studentResponse = transactionService.findById(id);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @GetMapping("All")
    public ResponseEntity<List<Transaction>> getAll(){
        List<Transaction> trans = transactionService.getAll();
        return new ResponseEntity<>(trans,HttpStatus.OK);
    }

}
