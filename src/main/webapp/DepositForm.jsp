<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deposit Form</title>
    <style>
        /* Add your CSS styles here */
    </style>
</head>
<body>
    <h2>Deposit Form</h2>
    <form action="DepositServlet" method="post">
        <label for="accountNumber">Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber"><br><br>
        
        <label for="amount">Amount:</label>
        <input type="text" id="amount" name="amount"><br><br>
        
        <button type="submit">Confirm Deposit</button>
    </form>
</body>
</html>
