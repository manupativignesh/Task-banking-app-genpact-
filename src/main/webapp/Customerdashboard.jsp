<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <style>
        body {
            background: linear-gradient(to right, #0066cc, #0099ff);
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            width: 600px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.2);
            position: relative;
            text-align: center;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .balance {
            font-size: 18px;
            color: #333;
            margin-top: 10px;
        }
        .button-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin-top: 20px;
        }
        .button {
            padding: 15px 30px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px;
        }
        .account {
            background-color: #007bff;
            color: #fff;
        }
        .transactions {
            background-color: #28a745;
            color: #fff;
        }
        .withdraw {
            background-color: #ffc107;
            color: #333;
        }
        .deposit {
            background-color: #17a2b8;
            color: #fff;
        }
        .delete {
            background-color: #dc3545;
            color: #fff;
        }
        .logout {
            background-color: #6c757d;
            color: #fff;
        }
        .button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Dashboard</h2>
        <div class="balance">
            Your Balance: <%= session.getAttribute("balance") != null ? session.getAttribute("balance") : "0.00" %>
        </div>
        <div class="button-container">
            <form action="AccountDetailsServlet" method="post">
                <button type="submit" class="button account">Account</button>
            </form>
            <form action="TransactionsServlet" method="post">
                <button type="submit" class="button transactions">Transactions</button>
            </form>
            <form action="withdraw.jsp" method="post">
                <button type="submit" class="button withdraw">Withdraw</button>
            </form>
            <form action="deposit.jsp" method="post">
                <button type="submit" class="button deposit">Deposit</button>
            </form>
            <form action="deleteaccount.jsp" method="post">
                <button type="submit" class="button delete">Delete</button>
            </form>
            <form action="LogoutServlet" method="post">
                <button type="submit" class="button logout">Logout</button>
            </form>
        </div>
    </div>
</body>
</html>
