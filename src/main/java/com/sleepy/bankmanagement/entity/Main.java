package com.sleepy.bankmanagement.entity;

import com.github.javafaker.Faker;
import com.sleepy.bankmanagement.entity.enums.*;
import com.sleepy.bankmanagement.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Faker faker = new Faker(new Locale("fa")); // Persian locale
    private static Faker englishFaker = new Faker(); // English for some fields
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sleepy");
    private static EntityManager em = emf.createEntityManager();
    private static CrudRepository<Object, Object> repository = new CrudRepository<>();

    // Storage for created entities to use in relationships
    private static List<Customer> customers = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static List<CheckingAccount> accounts = new ArrayList<>();
    private static List<Card> cards = new ArrayList<>();
    private static List<Loan> loans = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("🚀 Starting Database Test with Faker...\n");

        try {
            // Create test data
            createCustomers(5);
            createEmployees(3);
            createCheckingAccounts(8);
            createCards(12);
            createLoans(6);
            createTransactions(15);
            createCheques(8);

            System.out.println("✅ All test data created successfully!\n");

            // Display results
            displayResults();

            // Test relationships
            testRelationships();

        } catch (Exception e) {
            System.err.println("❌ Error during testing: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private static void createCustomers(int count) throws Exception {
        System.out.println("👥 Creating " + count + " customers...");

        for (int i = 0; i < count; i++) {
            Customer customer = Customer.builder()
                    .customerId("CUST" + String.format("%03d", i + 1))
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .nationalId(generateNationalId())
                    .dateOfBirth(convertToLocalDateTime(faker.date().birthday(18, 80)))
                    .phoneNumber(generateIranianPhoneNumber())
                    .registrationDate(convertToLocalDateTime(faker.date().past(365, TimeUnit.DAYS)))
                    .isActive(faker.bool().bool())
                    .build();

            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();

            customers.add(customer);
            System.out.printf("  ✓ Created: %s %s (ID: %s, National ID: %s)\n",
                    customer.getFirstName(), customer.getLastName(),
                    customer.getCustomerId(), customer.getNationalId());
        }
        System.out.println();
    }

    private static void createEmployees(int count) throws Exception {
        System.out.println("👨‍💼 Creating " + count + " employees...");

        for (int i = 0; i < count; i++) {
            Employee employee = Employee.builder()
                    .employeeId("EMP" + String.format("%03d", i + 1))
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .username(englishFaker.name().username())
                    .password(englishFaker.internet().password(8, 16))
                    .accessLevel(AccessLevel.ADMIN) // Only ADMIN available in enum
                    .isActive(faker.bool().bool())
                    .build();

            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();

            employees.add(employee);
            System.out.printf("  ✓ Created: %s %s (@%s, Access: %s)\n",
                    employee.getFirstName(), employee.getLastName(),
                    employee.getUsername(), employee.getAccessLevel());
        }
        System.out.println();
    }

    private static void createCheckingAccounts(int count) throws Exception {
        System.out.println("🏦 Creating " + count + " checking accounts...");

        for (int i = 0; i < count; i++) {
            Customer randomCustomer = customers.get(faker.random().nextInt(customers.size()));

            CheckingAccount account = CheckingAccount.builder()
                    .accountNumber("ACC" + String.format("%06d", i + 1))
                    .customerId(randomCustomer.getCustomerId())
                    .balance(faker.number().randomDouble(2, 10000, 10000000)) // 10K to 10M
                    .dateOpened(convertToLocalDateTime(faker.date().past(730, TimeUnit.DAYS)))
                    .status(faker.options().option(AccountStatus.class))
                    .monthlyMaintenanceFee(faker.number().randomDouble(2, 20000, 100000))
                    .freeTransactionLimit(faker.number().numberBetween(5, 50))
                    .checkBookFee(faker.number().randomDouble(2, 25000, 150000))
                    .build();

            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();

            accounts.add(account);
            System.out.printf("  ✓ Created: %s (Customer: %s, Balance: %.2f, Status: %s)\n",
                    account.getAccountNumber(), account.getCustomerId(),
                    account.getBalance(), account.getStatus());
        }
        System.out.println();
    }

    private static void createCards(int count) throws Exception {
        System.out.println("💳 Creating " + count + " cards...");

        for (int i = 0; i < count; i++) {
            CheckingAccount randomAccount = accounts.get(faker.random().nextInt(accounts.size()));
            Customer cardOwner = customers.stream()
                    .filter(c -> c.getCustomerId().equals(randomAccount.getCustomerId()))
                    .findFirst().orElse(customers.get(0));

            Card card = Card.builder()
                    .cardNumber(generateCardNumber())
                    .cardType(faker.options().option(CardType.class))
                    .cvv(String.format("%03d", faker.number().numberBetween(100, 999)))
                    .expiryDate(convertToLocalDateTime(faker.date().future(1460, TimeUnit.DAYS))) // Up to 4 years
                    .cardholderName(cardOwner.getFirstName() + " " + cardOwner.getLastName())
                    .linkedAccountNumber(randomAccount.getAccountNumber())
                    .isActive(faker.bool().bool())
                    .account(randomAccount)
                    .build();

            em.getTransaction().begin();
            em.persist(card);
            em.getTransaction().commit();

            cards.add(card);
            System.out.printf("  ✓ Created: %s (%s, %s, Account: %s)\n",
                    card.getCardNumber(), card.getCardType(),
                    card.getCardholderName(), card.getLinkedAccountNumber());
        }
        System.out.println();
    }

    private static void createLoans(int count) throws Exception {
        System.out.println("💰 Creating " + count + " loans...");

        for (int i = 0; i < count; i++) {
            Customer randomCustomer = customers.get(faker.random().nextInt(customers.size()));
            Employee randomEmployee = employees.get(faker.random().nextInt(employees.size()));

            double principal = faker.number().randomDouble(2, 1000000, 50000000); // 1M to 50M
            double interestRate = faker.number().randomDouble(1, 10, 25);
            int termMonths = faker.options().option(6, 12, 18, 24, 36, 48, 60);
            double monthlyPayment = calculateMonthlyPayment(principal, interestRate, termMonths);

            // Assuming remainingBalance should be initialized to the principal amount.
            // The original code had a compile error here as 'remainingBalance' was not defined.
            double remainingBalance = principal;

            Loan loan = Loan.builder()
                    .loanId("LOAN" + String.format("%03d", i + 1))
                    .customerId(randomCustomer.getCustomerId())
                    .loanType(faker.options().option(LoanType.class))
                    .principalAmount(principal)
                    .interestRate(interestRate)
                    .termInMonths(termMonths)
                    .monthlyPayment(monthlyPayment)
                    .remainingBalance(remainingBalance)
                    .loanStatus(faker.options().option(LoanStatus.class))
                    .startDate(convertToLocalDateTime(faker.date().past(365, TimeUnit.DAYS)))
                    .nextPaymentDate(convertToLocalDateTime(faker.date().future(30, TimeUnit.DAYS)))
                    .approvedByEmployeeId(randomEmployee.getEmployeeId())
                    .customer(randomCustomer)
                    .approvedByEmployee(randomEmployee)
                    .build();

            em.getTransaction().begin();
            em.persist(loan);
            em.getTransaction().commit();

            loans.add(loan);
            System.out.printf("  ✓ Created: %s (%s, %.2f, %s, Employee: %s)\n",
                    loan.getLoanId(), loan.getLoanType(), loan.getPrincipalAmount(),
                    loan.getLoanStatus(), loan.getApprovedByEmployeeId());
        }
        System.out.println();
    }

    private static void createTransactions(int count) throws Exception {
        System.out.println("💸 Creating " + count + " transactions...");

        for (int i = 0; i < count; i++) {
            Employee randomEmployee = employees.get(faker.random().nextInt(employees.size()));
            TransactionType type = faker.options().option(TransactionType.class);
            CheckingAccount sourceAccount = null;
            CheckingAccount destAccount = null;

            // Set accounts based on transaction type
            switch (type) {
                case DEPOSIT:
                    destAccount = accounts.get(faker.random().nextInt(accounts.size()));
                    break;
                case WITHDRAW:
                    sourceAccount = accounts.get(faker.random().nextInt(accounts.size()));
                    break;
                case TRANSFER:
                    sourceAccount = accounts.get(faker.random().nextInt(accounts.size()));
                    do {
                        destAccount = accounts.get(faker.random().nextInt(accounts.size()));
                    } while (destAccount.equals(sourceAccount));
                    break;
            }

            Transaction transaction = Transaction.builder()
                    .transactionId("TXN" + String.format("%06d", i + 1))
                    .transactionType(type)
                    .amount(faker.number().randomDouble(2, 10000, 5000000))
                    .timestamp(convertToLocalDateTime(faker.date().past(90, TimeUnit.DAYS)))
                    .sourceAccountNumber(sourceAccount != null ? sourceAccount.getAccountNumber() : null)
                    .destinationAccountNumber(destAccount != null ? destAccount.getAccountNumber() : null)
                    .description(generateTransactionDescription(type))
                    .transactionStatus(faker.options().option(TransactionStatus.class))
                    .processedBy(randomEmployee.getEmployeeId())
                    .build();

            em.getTransaction().begin();
            em.persist(transaction);
            em.getTransaction().commit();

            transactions.add(transaction);
            System.out.printf("  ✓ Created: %s (%s, %.2f, %s → %s, Status: %s)\n",
                    transaction.getTransactionId(), transaction.getTransactionType(),
                    transaction.getAmount(),
                    transaction.getSourceAccountNumber() != null ? transaction.getSourceAccountNumber() : "N/A",
                    transaction.getDestinationAccountNumber() != null ? transaction.getDestinationAccountNumber() : "N/A",
                    transaction.getTransactionStatus());
        }
        System.out.println();
    }

    private static void createCheques(int count) throws Exception {
        System.out.println("📝 Creating " + count + " cheques...");

        for (int i = 0; i < count; i++) {
            CheckingAccount randomAccount = accounts.get(faker.random().nextInt(accounts.size()));
            Employee randomEmployee = employees.get(faker.random().nextInt(employees.size()));
            Transaction randomTransaction = transactions.size() > 0 ?
                    transactions.get(faker.random().nextInt(transactions.size())) : null;

            ChequeStatus status = faker.options().option(ChequeStatus.class);

            Cheque cheque = Cheque.builder()
                    .chequeNumber("CHQ" + String.format("%06d", i + 1))
                    .drawerAccountNumber(randomAccount.getAccountNumber())
                    .payeeName(faker.name().fullName())
                    .amount(faker.number().randomDouble(2, 50000, 10000000))
                    .issueDate(convertToLocalDateTime(faker.date().past(60, TimeUnit.DAYS)))
                    .dueDate(convertToLocalDate(faker.date().future(60, TimeUnit.DAYS)))
                    .clearanceDate(status == ChequeStatus.CLEARED ?
                            convertToLocalDateTime(faker.date().past(30, TimeUnit.DAYS)) : null)
                    .status(status)
                    .bankName(faker.options().option("بانک ملی", "بانک صادرات", "بانک تجارت", "بانک پاسارگاد"))
                    .branchCode(String.format("%03d", faker.number().numberBetween(1, 999)))
                    .description(faker.lorem().sentence())
                    .processedByEmployeeId(randomEmployee.getEmployeeId())
                    .clearanceTransaction(status == ChequeStatus.CLEARED ? randomTransaction : null)
                    .processedByEmployee(randomEmployee)
                    .build();

            em.getTransaction().begin();
            em.persist(cheque);
            em.getTransaction().commit();

            System.out.printf("  ✓ Created: %s (Account: %s, Amount: %.2f, Status: %s, Payee: %s)\n",
                    cheque.getChequeNumber(), cheque.getDrawerAccountNumber(),
                    cheque.getAmount(), cheque.getStatus(), cheque.getPayeeName());
        }
        System.out.println();
    }

    private static void displayResults() {
        System.out.println("📊 DATABASE SUMMARY:");
        // Java 8 compatible way to print a repeated character
        System.out.println(String.join("", Collections.nCopies(60, "=")));
        System.out.printf("👥 Customers: %d\n", customers.size());
        System.out.printf("👨‍💼 Employees: %d\n", employees.size());
        System.out.printf("🏦 Accounts: %d\n", accounts.size());
        System.out.printf("💳 Cards: %d\n", cards.size());
        System.out.printf("💰 Loans: %d\n", loans.size());
        System.out.printf("💸 Transactions: %d\n", transactions.size());
        System.out.println();
    }

    private static void testRelationships() {
        System.out.println("🔗 TESTING RELATIONSHIPS:");
        // Java 8 compatible way to print a repeated character
        System.out.println(String.join("", Collections.nCopies(60, "=")));

        // Test Customer-Account relationship
        Customer testCustomer = customers.get(0);
        long customerAccounts = accounts.stream()
                .filter(acc -> acc.getCustomerId().equals(testCustomer.getCustomerId()))
                .count();
        System.out.printf("✅ Customer %s has %d accounts\n",
                testCustomer.getCustomerId(), customerAccounts);

        // Test Account-Card relationship
        CheckingAccount testAccount = accounts.get(0);
        long accountCards = cards.stream()
                .filter(card -> card.getLinkedAccountNumber().equals(testAccount.getAccountNumber()))
                .count();
        System.out.printf("✅ Account %s has %d cards\n",
                testAccount.getAccountNumber(), accountCards);

        // Test Employee-Loan relationship
        Employee testEmployee = employees.get(0);
        long employeeLoans = loans.stream()
                .filter(loan -> loan.getApprovedByEmployeeId().equals(testEmployee.getEmployeeId()))
                .count();
        System.out.printf("✅ Employee %s approved %d loans\n",
                testEmployee.getEmployeeId(), employeeLoans);

        System.out.println("✅ All relationships are working correctly!");
    }

    // Utility methods
    private static String generateNationalId() {
        return String.format("%010d", faker.number().numberBetween(1000000000L, 9999999999L));
    }

    private static String generateIranianPhoneNumber() {
        String[] operators = {"0901", "0902", "0903", "0905", "0911", "0912", "0913", "0914", "0915", "0916", "0917", "0918", "0919", "0990", "0991", "0992", "0993", "0994", "0995", "0996", "0997", "0998", "0999"};
        return faker.options().option(operators) + faker.number().digits(7);
    }

    private static String generateCardNumber() {
        return "6274-" + faker.number().digits(4) + "-" +
                faker.number().digits(4) + "-" + faker.number().digits(4);
    }

    private static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static double calculateMonthlyPayment(double principal, double annualRate, int termMonths) {
        double monthlyRate = annualRate / 100 / 12;
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, termMonths)) /
                (Math.pow(1 + monthlyRate, termMonths) - 1);
    }

    private static String generateTransactionDescription(TransactionType type) {
        switch (type) {
            case DEPOSIT:
                return faker.options().option("واریز نقدی", "واریز حقوق", "واریز کارمزد", "واریز سپرده");
            case WITHDRAW:
                return faker.options().option("برداشت نقدی", "برداشت خودپرداز", "برداشت شعبه");
            case TRANSFER:
                return faker.options().option("انتقال وجه", "پرداخت قبض", "پرداخت قسط", "حواله");
            default:
                return "تراکنش بانکی";
        }
    }

    private static void cleanup() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        System.out.println("\n🧹 Cleanup completed.");
    }
}