<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Created</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .success-container {
            background-color: #ffffff;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            overflow: hidden;
            width: 400px;
            max-width: 100%;
            text-align: center;
            padding: 30px;
        }
        .success-header {
            background-color: #28a745;
            color: #ffffff;
            padding: 20px;
            font-size: 28px;
            font-weight: bold;
            margin: -30px -30px 30px -30px;
            border-radius: 10px 10px 0 0;
        }
        .success-message {
            margin-bottom: 20px;
        }
        .home-button {
            background-color: #007bff;
            color: #ffffff;
            padding: 15px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .home-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <div class="success-header">Account Created</div>
        <div class="success-message">
            <p>Your account has been successfully created.</p>
        </div>
        <form action="index.jsp">
            <button class="home-button" type="submit">Go to Home</button>
        </form>
    </div>
</body>
</html>
