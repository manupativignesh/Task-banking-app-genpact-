<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Customer1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details</title>
    <style>
        body {
            background-color: #f0f0f0;
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Account Details</h2>
        <form action="AccountDetailsServlet" method="post">
            <label for="accountNumber">Account Number:</label>
            <input type="text" id="accountNumber" name="accountNumber" required>
            <div class="button-container">
                <input type="submit" value="View Details" class="button">
            </div>
        </form>
        <%
            Customer1 customer = (Customer1) request.getAttribute("customer");
            if (customer != null) {
        %>
        <table>
            <tr><th>Account Number</th><td><%= customer.getAccountNumber() %></td></tr>
            <tr><th>Full Name</th><td><%= customer.getFullName() %></td></tr>
            <tr><th>Address</th><td><%= customer.getAddress() %></td></tr>
            <tr><th>Mobile No</th><td><%= customer.getMobileNo() %></td></tr>
            <tr><th>Email ID</th><td><%= customer.getEmailId() %></td></tr>
            <tr><th>Account Type</th><td><%= customer.getAccountType() %></td></tr>
            <tr><th>Date of Birth</th><td><%= customer.getDateOfBirth() %></td></tr>
            <tr><th>ID Proof</th><td><%= customer.getIdProof() %></td></tr>
        </table>
        <div class="button-container">
            <form action="Customerdashboard.jsp">
                <input type="submit" value="OK" class="button">
            </form>
        </div>
        <% } else if (request.getAttribute("message") != null) { %>
        <p><%= request.getAttribute("message") %></p>
        <div class="button-container">
            <form action="Customerdashboard.jsp">
                <input type="submit" value="OK" class="button">
            </form>
        </div>
        <% } %>
    </div>
</body>
</html>
