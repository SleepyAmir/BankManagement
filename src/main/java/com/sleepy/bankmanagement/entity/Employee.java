package com.sleepy.bankmanagement.entity;




import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Employee {
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private AccessLevel accessLevel;
    private boolean isActive;
}
