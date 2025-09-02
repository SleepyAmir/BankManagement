package com.sleepy.bankmanagement.service;

import com.sleepy.bankmanagement.entity.Account;
import com.sleepy.bankmanagement.repository.AccountRepository;
import java.util.List;

public class AccountService {

    public Account save(Account account) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            return accountRepository.save(account);
        }
    }

    public Account edit(Account account) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            if (accountRepository.findById(account.getAccountNumber()) != null) {
                return accountRepository.edit(account);
            } else {
                throw new Exception("Account not found");
            }
        }
    }

    public Account deleteById(String id) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            Account accountToDelete = accountRepository.findById(id);
            if (accountToDelete != null) {
                return accountRepository.deleteById(id);
            } else {
                throw new Exception("Account not found");
            }
        }
    }

    public Account findById(String id) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            return accountRepository.findById(id);
        }
    }

    public List<Account> findAll() throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            return accountRepository.findAll();
        }
    }

    public List<Account> findByCustomerId(String customerId) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            return accountRepository.findByCustomerId(customerId);
        }
    }
}