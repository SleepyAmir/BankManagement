<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Loans</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body { background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh; }
        .main-card { background: white; border-radius: 16px;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1); margin-bottom: 2rem;
        }
        .card-header { background: linear-gradient(135deg, #f97316, #f59e0b); color: white; padding: 1.5rem; border: none;
        }
        .card-header h4 { margin: 0; font-weight: 600;
        }
        .form-section { padding: 2rem;
        }
        .form-row { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 1.5rem;
        }
        .table-container { background: white; border-radius: 16px;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1); }
        .table thead th { background: #f8fafc;
            border: none; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><i class="fas fa-university"></i> BankSys</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="customers">Customers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="accounts">Accounts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cards">Cards</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="loans">Loans</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="transactions">Transactions</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container py-5">
    <div class="main-card">
        <div class="card-header">
            <h4><i class="fas fa-hand-holding-dollar"></i> Create New Loan</h4>
        </div>
        <div class="form-section">
            <form action="loans" method="post">
                <div class="form-row">
                    <div class="mb-3">
                        <label for="loanId" class="form-label">Loan ID</label>
                        <input type="text" class="form-control" id="loanId" name="loanId" required>
                    </div>
                    <div class="mb-3">
                        <label for="customerId" class="form-label">Customer ID</label>
                        <input type="text" class="form-control" id="customerId" name="customerId" required>
                    </div>
                    <div class="mb-3">
                        <label for="principalAmount" class="form-label">Principal Amount</label>
                        <input type="number" step="0.01" class="form-control" id="principalAmount" name="principalAmount" required>
                    </div>
                    <div class="mb-3">
                        <label for="interestRate" class="