<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Account</title>
    <style>
        body {
            background-color: #f0f0f0;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            width: 400px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.2);
            text-align: center;
        }
        h2 {
            color: #333;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            text-align: left;
        }
        input[type='text'] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-top: 5px;
            box-sizing: border-box;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #dc3545;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px; /* Add margin for spacing */
        }
        .button:hover {
            background-color: #c82333;
        }
        .success-message {
            color: green;
        }
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Delete Account</h2>
        <% 
            String successParam = request.getParameter("success");
            if ("true".equals(successParam)) { 
        %>
            <p class="success-message">Account successfully deleted!</p>
        <% } else if ("false".equals(successParam)) { %>
            <p class="error-message">Error deleting account!</p>
            <% String errorMessage = request.getParameter("error");
               if (errorMessage != null) { %>
                  <p>Error Message: <%= errorMessage %></p>
            <% } %>
        <% } %>
        <form action="DeleteServlet" method="post">
            <label for="accountNumber">Account Number:</label>
            <input type="text" name="accountNumber" id="accountNumber" required>
            <div class="button-container">
                <input type="submit" value="Delete Account" class="button">
                <a href="adminDashboard.jsp" class="button">Go to Dashboard</a>
            </div>
        </form>
    </div>
</body>
</html>
