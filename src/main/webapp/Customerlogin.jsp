<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
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
            width: 400px;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.2);
            position: relative;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type='text'], input[type='password'] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-top: 5px;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .button, .change-password {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button {
            background-color: #007bff;
            color: #fff;
        }
        .button:hover {
            background-color: #0056b3;
        }
        .change-password {
            background-color: #ffcc00;
            color: #333;
            margin-right: auto;
        }
        .change-password:hover {
            background-color: #ffaa00;
        }
        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Login</h2>
        <% if (request.getParameter("error") != null) { %>
            <p class="error"><%= request.getParameter("error") %></p>
        <% } %>
        <form action="CustomerLoginServlet" method="post">
            <label>Account Number:</label>
            <input type="text" name="accountNumber" required><br><br>
            
            <label>Password:</label>
            <input type="password" name="password" required><br><br>
            
            <div class="button-container">
                <input type="submit" class="button" value="Login">
                <a href="Changepassword.jsp" class="change-password">Change Password</a>
            </div>
        </form>
    </div>
</body>
</html>
