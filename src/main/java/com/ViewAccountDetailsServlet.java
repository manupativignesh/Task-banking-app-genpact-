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

@WebServlet("/ViewAccountDetailsServlet")
public class ViewAccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewAccountDetailsServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

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

            // Display account details
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>View Account Details</title>");
            out.println("<style>");
            out.println("body { background-color: #f0f0f0; font-family: Arial, sans-serif;}");
            out.println(".container { width: 80%; margin: 0 auto; padding: 20px; background-color: #fff;");
            out.println("border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);}");
            out.println("h2 { color: #333; text-align: center; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("table, th, td { border: 1px solid #ccc;}");
            out.println("th, td { padding: 10px; text-align: left;}");
            out.println(".button-container { text-align: center; margin-top: 20px; }");
            out.println(".button { padding: 10px 20px; font-size: 16px; background-color: #007bff;");
            out.println("color: #fff; border: none; border-radius: 5px; cursor: pointer; }");
            out.println(".button:hover { background-color: #0056b3; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Account Details</h2>");

            if (result.next()) {
                out.println("<table>");
                out.println("<tr><th>Account Number</th><td>" + result.getString("accountNumber") + "</td></tr>");
                out.println("<tr><th>Full Name</th><td>" + result.getString("fullName") + "</td></tr>");
                out.println("<tr><th>Address</th><td>" + result.getString("address") + "</td></tr>");
                out.println("<tr><th>Mobile No</th><td>" + result.getString("mobileNo") + "</td></tr>");
                out.println("<tr><th>Email ID</th><td>" + result.getString("emailId") + "</td></tr>");
                out.println("<tr><th>Account Type</th><td>" + result.getString("accountType") + "</td></tr>");
                out.println("<tr><th>Date of Birth</th><td>" + result.getString("dateOfBirth") + "</td></tr>");
                out.println("<tr><th>ID Proof</th><td>" + result.getString("idProof") + "</td></tr>");
                out.println("</table>");

                // OK button to go back to admin dashboard
                out.println("<div class='button-container'>");
                out.println("<form action='adminDashboard.jsp'>"); // Change the action to adminDashboard.jsp or appropriate URL
                out.println("<input type='submit' value='OK' class='button'>");
                out.println("</form>");
                out.println("</div>");
            } else {
                out.println("<p>No account found with the provided account number!</p>");

                // OK button to go back to admin dashboard
                out.println("<div class='button-container'>");
                out.println("<form action='adminDashboard.jsp'>"); // Change the action to adminDashboard.jsp or appropriate URL
                out.println("<input type='submit' value='OK' class='button'>");
                out.println("</form>");
                out.println("</div>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Print to server logs
            out.println("<h3>Error: " + e.getMessage() + "</h3>"); // Display on the web page
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
            out.close();
        }
    }
}
