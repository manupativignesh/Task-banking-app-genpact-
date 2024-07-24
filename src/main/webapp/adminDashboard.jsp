<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            background-color: #f0f8ff;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
        }
        h1 {
            color: #333;
        }
        .button {
            display: inline-block;
            margin: 10px;
            padding: 15px 25px;
            font-size: 16px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Admin Dashboard</h1>
        <form action="createAccount.jsp" method="get">
            <button type="submit" class="button">Create Account</button>
        </form>
        <form action="delete_account.jsp" method="post">
            <button type="submit" class="button">Delete Account</button>
        </form>
        <form action="viewAccountDetails.jsp" method="get">
            <button type="submit" class="button">Account Details</button>
        </form>
        <form action="editAccountDetails.jsp" method="get">
            <button type="submit" class="button">Edit Details</button>
        </form>
        <form action="AdminLogin.jsp" method="get">
            <button type="submit" class="button">Logout</button>
        </form>
    </div>
</body>
</html>
