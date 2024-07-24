package com; // Replace 'com' with your actual package name

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

@WebServlet("/ConfirmDeleteServlet")
public class ConfirmDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConfirmDeleteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String accountNumber = request.getParameter("accountNumber");
        String password = request.getParameter("password");

        String jdbcURL = "jdbc:mysql://localhost:3306/new_banking_app";
        String jdbcUsername = "root";
        String jdbcPassword = "1234";

        String sqlSelect = "SELECT initialBalance FROM customer WHERE accountNumber = ?";
        String sqlDelete = "DELETE FROM customer WHERE accountNumber = ?";

        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            selectStatement = connection.prepareStatement(sqlSelect);
            selectStatement.setString(1, accountNumber);
            resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                double initialBalance = resultSet.getDouble("initialBalance");
                if (initialBalance == 0) {
                    // Delete the account
                    PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);
                    deleteStatement.setString(1, accountNumber);
                    int rowsDeleted = deleteStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        out.println("<html><body>");
                        out.println("<h3>Account deleted successfully!</h3>");
                        out.println("<p><a href=\"Customerdashboard.jsp\">Go to Dashboard</a></p>");
                        out.println("</body></html>");
                    } else {
                        out.println("<html><body>");
                        out.println("<h3>Error deleting account!</h3>");
                        out.println("</body></html>");
                    }
                } else {
                    out.println("<html><body>");
                    out.println("<h3>Account cannot be deleted because initial balance is not zero!</h3>");
                    out.println("<p><a href=\"Customerdashboard.jsp\">Go to Dashboard</a></p>");
                    out.println("</body></html>");
                }
            } else {
                out.println("<html><body>");
                out.println("<h3>No account found with the provided account number!</h3>");
                out.println("</body></html>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (selectStatement != null) selectStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.close();
        }
    }
}
