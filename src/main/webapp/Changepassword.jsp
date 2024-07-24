<!DOCTYPE html>
<html>
<head>
    <title>Change Password</title>
    <style>
        /* Your existing styles */
    </style>
</head>
<body>
    <div class="container">
        <h2>Change Password</h2>
        <form action="ChangePasswordServlet" method="post">
            <label>Account Number:</label>
            <input type="text" name="accountNumber" required><br><br>

            <label>Old Password:</label>
            <input type="password" name="oldPassword" required><br><br>

            <label>New Password:</label>
            <input type="password" name="newPassword" required><br><br>

            <input type="submit" value="Change Password">
        </form>
        <% if (request.getParameter("error") != null) { %>
            <p style="color: red;"><%= request.getParameter("error") %></p>
        <% } %>
    </div>
</body>
</html>
