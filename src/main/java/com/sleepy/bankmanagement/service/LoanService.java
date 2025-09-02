package com.sleepy.bankmanagement.service;

import com.sleepy.bankmanagement.entity.Loan;
import com.sleepy.bankmanagement.repository.LoanRepository;
import java.util.List;

public class LoanService {

    public Loan save(Loan loan) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.save(loan);
        }
    }

    public Loan edit(Loan loan) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            if (loanRepository.findById(loan.getLoanId()) != null) {
                return loanRepository.edit(loan);
            } else {
                throw new Exception("Loan not found");
            }
        }
    }

    public Loan deleteById(String id) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            Loan loanToDelete = loanRepository.findById(id);
            if (loanToDelete != null) {
                return loanRepository.deleteById(id);
            } else {
                throw new Exception("Loan not found");
            }
        }
    }

    public Loan findById(String id) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.findById(id);
        }
    }

    public List<Loan> findAll() throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.findAll();
        }
    }

    public List<Loan> findByCustomerId(String customerId) throws Exception {
        try (LoanRepository loanRepository = new LoanRepository()) {
            return loanRepository.findByCustomerId(customerId);
        }
    }


}