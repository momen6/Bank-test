package com.example.banktest.controller;

import com.example.banktest.model.Account;
import com.example.banktest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam String username, @RequestParam BigDecimal initialBalance) {
        Account account = this.accountService.createAccount(username, initialBalance);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Account>> getUserAccounts(@PathVariable String username){
        List<Account> userAccounts = this.accountService.getUserAccounts(username);
        return new ResponseEntity<>(userAccounts, HttpStatus.OK);
    }
}
