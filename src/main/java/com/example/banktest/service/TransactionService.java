package com.example.banktest.service;

import com.example.banktest.model.Account;
import com.example.banktest.model.Transaction;
import com.example.banktest.repository.AccountRepository;
import com.example.banktest.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transaction makeDeposit(Long accountId, BigDecimal amount) {
        //find account by ID
        Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account Not Found!!"));

        //update Account balance
        account.setBalance(account.getBalance().add(amount));

        //save the updated account to the database
        this.accountRepository.save(account);

        //create a deposit transaction
        Transaction transaction = new Transaction();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setAccount(account);

        //save the transaction to the database
        return this.transactionRepository.save(transaction);
    }

    public Transaction makeWithdrawal(Long accountId, BigDecimal amount) {
        Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account Not Found!!"));

        //check if account has Funds
        if (account.getBalance().compareTo(amount) < 0)
            throw new IllegalArgumentException("Insufficient funds for withdrawal");

        //update the account balance
        account.setBalance(account.getBalance().subtract(amount));

        //save updated account to the database
        this.accountRepository.save(account);

        //create withdrawal transaction
        Transaction transaction = new Transaction();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setAmount(amount.negate()); // Withdrawals are negative amounts
        transaction.setAccount(account);

        return this.transactionRepository.save(transaction);
    }

    public List<Transaction> getAccountTransactions(Long accountId){
        Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account Not Found!!"));

        return this.transactionRepository.findByAccount(account);
    }
}
