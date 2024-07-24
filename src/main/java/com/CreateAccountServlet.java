package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateAccountServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String emailId = request.getParameter("emailId");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        String idProof = request.getParameter("idProof");

        // Generate account number and password
        String accountNumber = "ACC123456" + generateAccountNumber();
        String password = "pass123";

        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/new_banking_app";
        String jdbcUsername = "root";
        String jdbcPassword = "1234";

        // SQL query
        String sql = "INSERT INTO customer (fullName, address, mobileNo, emailId, accountType, initialBalance, "
                   + "dateOfBirth, idProof, accountNumber, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Create PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fullName);
            statement.setString(2, address);
            statement.setString(3, mobileNo);
            statement.setString(4, emailId);
            statement.setString(5, accountType);
            statement.setDouble(6, initialBalance);
            statement.setString(7, dateOfBirth);
            statement.setString(8, idProof);
            statement.setString(9, accountNumber);
            statement.setString(10, password);

            // Execute the query
            int rows = statement.executeUpdate();

            // Check if insertion was successful
            if (rows > 0) {
                out.println("<html><body>");
                out.println("<h3>Account created successfully!</h3>");
                out.println("<p>Account Number: <input type='text' value='" + accountNumber + "' readonly></p>");
                out.println("<p>Password: <input type='text' value='" + password + "' readonly></p>");
                out.println("<form action='adminDashboard.jsp' method='get'>");
                out.println("<button type='submit'>Go to Dashboard</button>");
                out.println("</form>");
                out.println("</body></html>");
            } else {
                out.println("<h3>Error creating account!</h3>");
            }

            // Close connection
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Print to server logs
            out.println("<h3>Error: " + e.getMessage() + "</h3>"); // Display on the web page
        } finally {
            out.close();
        }
    }

    // Method to generate a simple account number (example)
    private int generateAccountNumber() {
        // Implement your logic to generate a unique account number
        // Example: Generating a random number for simplicity
        return (int) (Math.random() * 10000); // Adjust as per your application's requirements
    }
}
