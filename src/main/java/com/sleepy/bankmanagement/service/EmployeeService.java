package com.sleepy.bankmanagement.service;

import com.sleepy.bankmanagement.entity.Employee;
import com.sleepy.bankmanagement.repository.EmployeeRepository;
import java.util.List;

public class EmployeeService {

    public Employee save(Employee employee) throws Exception {
        try (EmployeeRepository employeeRepository = new EmployeeRepository()) {
            return employeeRepository.save(employee);
        }
    }

    public Employee edit(Employee employee) throws Exception {
        try (EmployeeRepository employeeRepository = new EmployeeRepository()) {
            if (employeeRepository.findById(employee.getEmployeeId()) != null) {
                return employeeRepository.edit(employee);
            } else {
                throw new Exception("Employee not found");
            }
        }
    }

    public Employee deleteById(String id) throws Exception {
        try (EmployeeRepository employeeRepository = new EmployeeRepository()) {
            Employee employeeToDelete = employeeRepository.findById(id);
            if (employeeToDelete != null) {
                return employeeRepository.deleteById(id);
            } else {
                throw new Exception("Employee not found");
            }
        }
    }

    public Employee findById(String id) throws Exception {
        try (EmployeeRepository employeeRepository = new EmployeeRepository()) {
            return employeeRepository.findById(id);
        }
    }

    public List<Employee> findAll() throws Exception {
        try (EmployeeRepository employeeRepository = new EmployeeRepository()) {
            return employeeRepository.findAll();
        }
    }

    public Employee findByUsername(String username) throws Exception {
        try (EmployeeRepository employeeRepository = new EmployeeRepository()) {
            return employeeRepository.findByUsername(username);
        }
    }
}