package com; // Replace 'com' with your actual package name where the servlet class resides

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAccountDetailsServlet")
public class UpdateAccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateAccountDetailsServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve parameters from the form submission
        String accountNumber = request.getParameter("accountNumber");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/new_banking_app";
        String jdbcUsername = "root";
        String jdbcPassword = "1234";

        // SQL query to update account details
        String sql = "UPDATE customer SET fullName=?, address=?, mobileNo=?, emailId=?, accountType=?, initialBalance=?, dateOfBirth=?, idProof=? WHERE accountNumber=?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            statement = connection.prepareStatement(sql);
            statement.setString(1, fullName);
            statement.setString(2, address);
            statement.setString(3, mobileNo);
            statement.setString(4, emailId);
            statement.setString(5, accountType);
            statement.setDouble(6, initialBalance);
            statement.setString(7, dateOfBirth);
            statement.setString(8, idProof);
            statement.setString(9, accountNumber);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                // Account details updated successfully message
                response.getWriter().println("Account details updated successfully.<br>");
                // Option to go back to Admin Dashboard
                response.getWriter().println("<a href='adminDashboard.jsp'>Go to Dashboard</a>");
            } else {
                response.getWriter().println("Failed to update account details.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
