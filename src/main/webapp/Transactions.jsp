<%@ page import="java.util.List" %>
<%@ page import="com.Transaction" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recent Transactions</title>
    <style>
        /* Your CSS styles for the table */
    </style>
</head>
<body>
    <h2>Recent Transactions</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Account Number</th>
                <th>Transaction Type</th>
                <th>Amount</th>
                <th>Description</th>
                <th>Transaction Date</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
                if (transactions != null) {
                    for (Transaction transaction : transactions) {
            %>
                        <tr>
                            <td><%= transaction.getId() %></td>
                            <td><%= transaction.getAccountNumber() %></td>
                            <td><%= transaction.getTransactionType() %></td>
                            <td><%= transaction.getAmount() %></td>
                            <td><%= transaction.getDescription() %></td>
                            <td><%= transaction.getTransactionDate() %></td>
                        </tr>
            <% 
                    }
                } else {
            %>
                    <tr>
                        <td colspan="6">No transactions found.</td>
                    </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>
