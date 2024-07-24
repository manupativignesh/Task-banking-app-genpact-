package com; // Replace 'com' with your actual package name where the servlet class resides

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/AccountDetailsServlet")
public class AccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AccountDetailsServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve account number to view
        String accountNumber = request.getParameter("accountNumber");

        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/new_banking_app";
        String jdbcUsername = "root";
        String jdbcPassword = "1234";

        // SQL query
        String sql = "SELECT * FROM customer WHERE accountNumber = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            // Load JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Create PreparedStatement
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);

            // Execute the query
            result = statement.executeQuery();

            if (result.next()) {
                // Create a Customer1 object to store the retrieved details
                Customer1 customer = new Customer1();
                customer.setAccountNumber(result.getString("accountNumber"));
                customer.setFullName(result.getString("fullName"));
                customer.setAddress(result.getString("address"));
                customer.setMobileNo(result.getString("mobileNo"));
                customer.setEmailId(result.getString("emailId"));
                customer.setAccountType(result.getString("accountType"));
                customer.setDateOfBirth(result.getString("dateOfBirth"));
                customer.setIdProof(result.getString("idProof"));

                // Set the customer object in the request attribute
                request.setAttribute("customer", customer);
            } else {
                // If no account found, set a message attribute
                request.setAttribute("message", "");
            }

            // Forward the request to the JSP page
            request.getRequestDispatcher("AccountDetails.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Print to server logs
            request.setAttribute("message", "Error: " + e.getMessage()); // Set error message in the request attribute
            request.getRequestDispatcher("AccountDetails.jsp").forward(request, response); // Forward to JSP with error message
        } finally {
            // Close resources
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log any closing exceptions
            }
        }
    }
}
