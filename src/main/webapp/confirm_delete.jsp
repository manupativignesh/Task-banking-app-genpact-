<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirm Delete Account</title>
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
        .message {
            margin-bottom: 20px;
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
        }
        .button:hover {
            background-color: #c82333;
        }
        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
        .success-message {
            color: green;
            font-size: 14px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Delete Account</h2>
        <div class="message">
            <p>Are you sure you want to close your account?</p>
        </div>
        <div class="button-container">
            <form action="ConfirmDeleteServlet" method="post">
                <input type="hidden" name="accountNumber" value="${param.accountNumber}">
                <input type="hidden" name="password" value="${param.password}">
                <input type="submit" value="Yes, Close Account" class="button">
            </form>
            <p style="margin-top: 10px;"><a href="Customerdashboard.jsp">Cancel</a></p>
            
            <%-- Display error or success messages --%>
            <% if (request.getAttribute("error") != null) { %>
                <p class="error-message">${request.getAttribute("error")}</p>
            <% } else if (request.getAttribute("message") != null) { %>
                <p class="success-message">${request.getAttribute("message")}</p>
            <% } %>
        </div>
    </div>
</body>
</html>
