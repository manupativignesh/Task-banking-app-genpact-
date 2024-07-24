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

@WebServlet("/EditAccountDetailsServlet")
public class EditAccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditAccountDetailsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String accountNumber = request.getParameter("accountNumber");

        String jdbcURL = "jdbc:mysql://localhost:3306/new_banking_app";
        String jdbcUsername = "root";
        String jdbcPassword = "1234";

        String sqlSelect = "SELECT * FROM customer WHERE accountNumber = ?";

        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet result = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            selectStatement = connection.prepareStatement(sqlSelect);
            selectStatement.setString(1, accountNumber);
            result = selectStatement.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Edit Account Details</title>");
            out.println("<style>");
            out.println("body { background-color: #f0f0f0; font-family: Arial, sans-serif;}");
            out.println(".container { width: 80%; margin: 0 auto; padding: 20px; background-color: #fff;");
            out.println("border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);}");
            out.println("h2 { color: #333; text-align: center; }");
            out.println("form { margin-top: 20px; }");
            out.println("label { display: block; margin-bottom: 10px; }");
            out.println("input[type='text'], input[type='number'], input[type='email'], select { width: 100%; padding: 10px;");
            out.println("font-size: 16px; border-radius: 5px; border: 1px solid #ccc; margin-top: 5px; }");
            out.println(".button-container { text-align: center; margin-top: 20px; }");
            out.println(".button { padding: 10px 20px; font-size: 16px; background-color: #007bff;");
            out.println("color: #fff; border: none; border-radius: 5px; cursor: pointer; }");
            out.println(".button:hover { background-color: #0056b3; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Edit Account Details</h2>");

            if (result.next()) {
                out.println("<form action='UpdateAccountDetailsServlet' method='post'>");
                out.println("<input type='hidden' name='accountNumber' value='" + accountNumber + "'>");
                out.println("<label>Full Name:</label><input type='text' name='fullName' value='" + result.getString("fullName") + "' required><br>");
                out.println("<label>Address:</label><input type='text' name='address' value='" + result.getString("address") + "' required><br>");
                out.println("<label>Mobile No:</label><input type='text' name='mobileNo' value='" + result.getString("mobileNo") + "' required><br>");
                out.println("<label>Email ID:</label><input type='email' name='emailId' value='" + result.getString("emailId") + "' required><br>");
                out.println("<label>Account Type:</label>");
                out.println("<select name='accountType' required>");
                out.println("<option value='Saving' " + (result.getString("accountType").equals("Saving") ? "selected" : "") + ">Saving</option>");
                out.println("<option value='Current' " + (result.getString("accountType").equals("Current") ? "selected" : "") + ">Current</option>");
                out.println("</select><br>");
                out.println("<label>Initial Balance:</label><input type='number' name='initialBalance' value='" + result.getDouble("initialBalance") + "' min='1000' required><br>");
                out.println("<label>Date of Birth:</label><input type='date' name='dateOfBirth' value='" + result.getString("dateOfBirth") + "' required><br>");
                out.println("<label>ID Proof:</label><input type='text' name='idProof' value='" + result.getString("idProof") + "' required><br>");
                out.println("<div class='button-container'>");
                out.println("<input type='submit' value='Update Account' class='button'>");
                out.println("</div>");
                out.println("</form>");
            } else {
                out.println("<p>No account found with the provided account number!</p>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.close();
        }
    }
}
