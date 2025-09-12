package com.sleepy.bankmanagement.service;

import com.sleepy.bankmanagement.entity.SavingsAccount;
import com.sleepy.bankmanagement.repository.SavingsAccountRepository;
import java.util.List;

public class SavingsAccountService {

    public SavingsAccount save(SavingsAccount account) throws Exception {
        try (SavingsAccountRepository repository = new SavingsAccountRepository()) {
            return repository.save(account);
        }
    }

    public SavingsAccount edit(SavingsAccount account) throws Exception {
        try (SavingsAccountRepository repository = new SavingsAccountRepository()) {
            if (repository.findById(account.getAccountNumber()) != null) {
                return repository.edit(account);
            } else {
                throw new Exception("Savings account not found");
            }
        }
    }

    public SavingsAccount deleteById(String id) throws Exception {
        try (SavingsAccountRepository repository = new SavingsAccountRepository()) {
            SavingsAccount accountToDelete = repository.findById(id);
            if (accountToDelete != null) {
                return repository.deleteById(id);
            } else {
                throw new Exception("Savings account not found");
            }
        }
    }

    public SavingsAccount findById(String id) throws Exception {
        try (SavingsAccountRepository repository = new SavingsAccountRepository()) {
            return repository.findById(id);
        }
    }

    public List<SavingsAccount> findAll() throws Exception {
        try (SavingsAccountRepository repository = new SavingsAccountRepository()) {
            return repository.findAll();
        }
    }

    public List<SavingsAccount> findByCustomerId(String customerId) throws Exception {
        try (SavingsAccountRepository repository = new SavingsAccountRepository()) {
            return repository.findByCustomerId(customerId);
        }
    }

    public List<SavingsAccount> findByInterestRateRange(double minRate, double maxRate) throws Exception {
        try (SavingsAccountRepository repository = new SavingsAccountRepository()) {
            return repository.findByInterestRateRange(minRate, maxRate);
        }
    }

    public List<SavingsAccount> findBelowMinimumBalance() throws Exception {
        try (SavingsAccountRepository repository = new SavingsAccountRepository()) {
            return repository.findBelowMinimumBalance();
        }
    }


}