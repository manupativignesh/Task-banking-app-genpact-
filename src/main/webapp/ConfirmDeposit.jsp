<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirm Deposit</title>
    <style>
        /* Add your CSS styles here */
    </style>
</head>
<body>
    <h2>Confirm Deposit</h2>
    <p>Account Number: <%= session.getAttribute("accountNumber") %></p>
    <p>Deposit Amount: <%= session.getAttribute("depositAmount") %></p>
    <p>Do you want to confirm this deposit?</p>
    <form action="DepositConfirmServlet" method="post">
        <input type="hidden" name="accountNumber" value="<%= session.getAttribute("accountNumber") %>">
        <input type="hidden" name="amount" value="<%= session.getAttribute("depositAmount") %>">
        <button type="submit">Confirm</button>
    </form>
    <a href="DepositForm.jsp">Cancel</a>
</body>
</html>
