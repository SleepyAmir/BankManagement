<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Cards</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body { background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh; }
        .main-card { background: white; border-radius: 16px;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1); margin-bottom: 2rem;
        }
        .card-header { background: linear-gradient(135deg, #64748b, #334155); color: white; padding: 1.5rem; border: none;
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
            <h4><i class="fas fa-credit-card"></i> Issue New Card</h4>
        </div>
        <div class="form-section">
            <form action="cards" method="post">
                <div class="form-row">
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber" required>
                    </div>
                    <div class="mb-3">
                        <label for="linkedAccountNumber" class="form-label">Linked Account Number</label>
                        <input type="text" class="form-control" id="linkedAccountNumber" name="linkedAccountNumber" required>
                    </div>
                    <div class="mb-3">
                        <label for="cvv" class="form-label">CVV</label>
                        <input type="text" class="form-control" id="cvv" name="cvv" required>
                    </div>
                    <div class="mb-3">
                        <label for="expirationDate" class="form-label">Expiry Date</label>
                        <input type="date" class="form-control" id="expirationDate" name="expirationDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="cardType" class="form-label">Card Type</label>
                        <select class="form-select" id="cardType" name="cardType">
                            <option value="DEBIT">DEBIT</option>
                            <option value="CREDIT">CREDIT</option>
                            <option value="GIFT_CARD">GIFT_CARD</option>
                        </select>
                    </div>
                </div>
                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-dark btn-lg"><i class="fas fa-save"></i> Save Card</button>
                </div>
            </form>
        </div>
    </div>

    <div class="table-container">
        <div class="card-header">
            <h4><i class="fas fa-list-ul"></i> Existing Cards</h4>
        </div>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Card Number</th>
                    <th>Linked Account</th>
                    <th>Type</th>
                    <th>Expiry Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="card" items="${sessionScope.cardList}">
                    <tr class="align-middle">
                        <td>${card.cardNumber}</td>
                        <td>${card.linkedAccountNumber}</td>
                        <td>${card.cardType}</td>
                        <td>${card.expiryDate}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="alert('Edit card: ${card.cardNumber}')"><i class="fas fa-edit"></i></button>
                            <button class="btn btn-danger btn-sm" onclick="alert('Remove card: ${card.cardNumber}')"><i class="fas fa-trash"></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>