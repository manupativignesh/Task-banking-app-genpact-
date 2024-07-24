package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber");

        if (accountNumber == null) {
            response.sendRedirect("Customerlogin.jsp");
            return;
        }

        String balance = getInitialBalance(accountNumber);

        // Debugging: Print the balance to the console
        System.out.println("Fetched Balance: " + balance);

        // Set balance in session attribute to be accessed in JSP
        session.setAttribute("balance", balance);

        // Forward to JSP to display balance
        request.getRequestDispatcher("Customerdashboard.jsp").forward(request, response);
    }

    // Method to retrieve initial balance from database
    private String getInitialBalance(String accountNumber) {
        String balance = "0.00";
        String jdbcUrl = "jdbc:mysql://localhost:3306/new_banking_app";
        String dbUser = "root";
        String dbPassword = "1234";
        String sql = "SELECT initialBalance FROM Customer WHERE accountNumber = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                balance = rs.getString("initialBalance");
                // Debugging: Print the balance fetched from the database
                System.out.println("Database Balance: " + balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }
}
