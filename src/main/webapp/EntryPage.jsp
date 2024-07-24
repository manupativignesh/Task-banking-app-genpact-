<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer's Bank</title>
    <style>
        body {
            background: linear-gradient(to right, #1e3c72, #2a5298); /* Attractive background gradient */
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 3em;
            color: #333;
            margin-bottom: 20px;
        }
        .button-container {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }
        .button {
            padding: 15px 30px;
            font-size: 1.2em;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>CUSTOMER'S BANK</h1>
        <div class="button-container">
            <a href="AdminLogin.jsp" class="button">Admin</a>
            <a href="Customerlogin.jsp" class="button">Customer</a>
        </div>
    </div>
</body>
</html>