package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TransactionsServlet")
public class TransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
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
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");

        if (accountNumber == null) {
            response.sendRedirect("Customerlogin.jsp?error=Session expired. Please log in again.");
            return;
        }

        List<Transaction> transactions = getTransactions(accountNumber);

        // Set transactions in request attribute to be accessed in JSP
        request.setAttribute("transactions", transactions);

        // Forward to JSP to display transactions
        request.getRequestDispatcher("Transactions.jsp").forward(request, response);
    }

    // Method to retrieve transactions from database
    private List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        String jdbcUrl = DB_URL + DB_NAME;
        String sql = "SELECT * FROM transactions WHERE accountNumber = ? AND (transactionType = 'deposit' OR transactionType = 'withdrawal') ORDER BY transactionDate DESC LIMIT 10";

        try (
            Connection conn = DriverManager.getConnection(jdbcUrl, DB_USERNAME, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String transactionType = rs.getString("transactionType");
                double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                java.util.Date transactionDate = rs.getTimestamp("transactionDate");

                Transaction transaction = new Transaction(id, accountNumber, transactionType, amount, description, transactionDate);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            System.out.println("Failed to retrieve transactions for accountNumber: " + accountNumber);
        }

        return transactions;
    }
}
