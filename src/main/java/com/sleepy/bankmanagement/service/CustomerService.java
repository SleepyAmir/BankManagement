package com.sleepy.bankmanagement.service;

import com.sleepy.bankmanagement.entity.Customer;
import com.sleepy.bankmanagement.repository.CustomerRepository;
import java.util.List;

public class CustomerService {

    public Customer save(Customer customer) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.save(customer);
        }
    }

    public Customer edit(Customer customer) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            if (customerRepository.findById(customer.getCustomerId()) != null) {
                return customerRepository.edit(customer);
            } else {
                throw new Exception("Customer not found");
            }
        }
    }

    public Customer deleteById(String id) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            Customer customerToDelete = customerRepository.findById(id);
            if (customerToDelete != null) {
                return customerRepository.deleteById(id);
            } else {
                throw new Exception("Customer not found");
            }
        }
    }

    public Customer findById(String id) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findById(id);
        }
    }

    public List<Customer> findAll() throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findAll();
        }
    }

    public Customer findByNationalId(String nationalId) throws Exception {
        try (CustomerRepository customerRepository = new CustomerRepository()) {
            return customerRepository.findByNationalId(nationalId);
        }
    }
}