package com.example.banktest.controller;

import com.example.banktest.model.Transaction;
import com.example.banktest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> makeDeposit(@RequestParam Long accountId, @RequestParam BigDecimal amount){
        Transaction transaction = this.transactionService.makeDeposit(accountId,amount);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
    @PostMapping("/withdrawal")
    public ResponseEntity<Transaction> makeWithdrawal(@RequestParam Long accountId, @RequestParam BigDecimal amount){
        Transaction transaction = this.transactionService.makeWithdrawal(accountId,amount);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable Long id){
        List<Transaction> transactions = this.transactionService.getAccountTransactions(id);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }
}
