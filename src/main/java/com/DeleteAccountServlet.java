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

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteAccountServlet() {
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

        String sqlSelect = "SELECT password FROM customer WHERE accountNumber = ?";
        String sqlDelete = "DELETE FROM customer WHERE accountNumber = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
            selectStatement.setString(1, accountNumber);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                if (storedPassword.equals(password)) {
                    PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);
                    deleteStatement.setString(1, accountNumber);
                    int rowsDeleted = deleteStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        out.println("<html><body>");
                        out.println("<h3>Account deleted successfully!</h3>");
                        out.println("</body></html>");
                    } else {
                        out.println("<html><body>");
                        out.println("<h3>Error deleting account!</h3>");
                        out.println("</body></html>");
                    }
                } else {
                    out.println("<html><body>");
                    out.println("<h3>Invalid password!</h3>");
                    out.println("</body></html>");
                }
            } else {
                out.println("<html><body>");
                out.println("<h3>No account found with the provided account number!</h3>");
                out.println("</body></html>");
            }

            resultSet.close();
            selectStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            out.close();
        }
    }
}
