package com.sleepy.bankmanagement.service;

import com.sleepy.bankmanagement.entity.Transaction;
import com.sleepy.bankmanagement.repository.TransactionRepository;
import java.util.List;

public class TransactionService {

    public Transaction save(Transaction transaction) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.save(transaction);
        }
    }

    public Transaction edit(Transaction transaction) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            if (transactionRepository.findById(transaction.getTransactionId()) != null) {
                return transactionRepository.edit(transaction);
            } else {
                throw new Exception("Transaction not found");
            }
        }
    }

    public Transaction deleteById(String id) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            Transaction transactionToDelete = transactionRepository.findById(id);
            if (transactionToDelete != null) {
                return transactionRepository.deleteById(id);
            } else {
                throw new Exception("Transaction not found");
            }
        }
    }

    public Transaction findById(String id) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findById(id);
        }
    }

    public List<Transaction> findAll() throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findAll();
        }
    }

    public List<Transaction> findByAccountNumber(String accountNumber) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findByAccountNumber(accountNumber);
        }
    }
}