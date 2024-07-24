<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Withdraw Failed</title>
    <style>
        body {
            background-color: #f8d7da;
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
            color: #721c24;
        }
        .back-button {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #6c757d;
            color: #fff;
        }
        .back-button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Withdrawal Failed</h2>
        <p>There was an error processing your withdrawal. Please try again.</p>
        <form action="Customerdashboard.jsp" method="get">
            <button type="submit" class="back-button">Back to Dashboard</button>
        </form>
    </div>
</body>
</html>
