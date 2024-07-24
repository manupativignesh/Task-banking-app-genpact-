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

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/new_banking_app";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String password = request.getParameter("password");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String query = "SELECT * FROM Customer WHERE accountNumber = ? AND password = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, accountNumber);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("accountNumber", accountNumber);
                session.setAttribute("customerName", rs.getString("fullName"));
                session.setAttribute("balance", rs.getDouble("initialBalance")); // Set balance in session
                response.sendRedirect("Customerdashboard.jsp");
            } else {
                response.sendRedirect("Customerlogin.jsp?error=Invalid account number or password.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle error - redirect to error page or display an error message
            response.sendRedirect("Customerlogin.jsp?error=Database connection error.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
