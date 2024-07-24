package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "new_banking_app"; // Replace with your database name
    private static final String DB_USERNAME = "root"; // Replace with your database username
    private static final String DB_PASSWORD = "1234"; // Replace with your database password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");

        if (accountNumber == null) {
            response.sendRedirect("Customerlogin.jsp?error=Session expired. Please log in again.");
            return;
        }

        double withdrawAmount = Double.parseDouble(request.getParameter("amount"));

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USERNAME, DB_PASSWORD);
            connection.setAutoCommit(false); // Start transaction

            // Update Customer table with withdrawal
            String updateQuery = "UPDATE Customer SET initialBalance = initialBalance - ? WHERE accountNumber = ? AND initialBalance >= ?";
            ps = connection.prepareStatement(updateQuery);

            ps.setDouble(1, withdrawAmount);
            ps.setString(2, accountNumber);
            ps.setDouble(3, withdrawAmount);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                // Withdrawal successful, now insert into transactions table
                String insertTransactionQuery = "INSERT INTO transactions (accountNumber, transactionType, amount, description, transactionDate) VALUES (?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(insertTransactionQuery);

                ps.setString(1, accountNumber);
                ps.setString(2, "withdrawal");
                ps.setDouble(3, withdrawAmount);
                ps.setString(4, "Withdrawal from account");
                ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

                ps.executeUpdate();

                connection.commit(); // Commit transaction
                session.setAttribute("balance", -withdrawAmount); // Update session balance
                response.sendRedirect("WithdrawSuccess.jsp");
            } else {
                // Withdrawal failed
                response.sendRedirect("WithdrawFailure.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback transaction on error
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true); // Reset auto-commit mode
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.close();
        }
    }
}
