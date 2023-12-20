package com.example.banktest.service;

import com.example.banktest.model.Account;
import com.example.banktest.model.User;
import com.example.banktest.repository.AccountRepository;
import com.example.banktest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    public Account createAccount(String username, BigDecimal initialBalance){
        //find the user by username
        User user = this.userRepository.findUserByUsername(username);
        if (user == null)
            throw new IllegalArgumentException("User Not Found");

        //create new account
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(initialBalance);
        account.setUser(user);

        //save account to database
        return this.accountRepository.save(account);
    }

    public List<Account> getUserAccounts(String username){
        User user = this.userRepository.findUserByUsername(username);
        if (user == null)
            throw new IllegalArgumentException("User Not Found");
        return this.accountRepository.findByUser(user);
    }

    private String generateAccountNumber(){
        return String.valueOf(new Random().nextInt(1000000));
    }

}
