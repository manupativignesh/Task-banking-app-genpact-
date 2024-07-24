package com;

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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        String jdbcURL = "jdbc:mysql://localhost:3306/new_banking_app";
        String jdbcUsername = "root";
        String jdbcPassword = "1234";

        String sqlDelete = "DELETE FROM customer WHERE accountNumber = ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
            
            stmt.setString(1, accountNumber);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                response.sendRedirect("delete_account.jsp?success=true");
            } else {
                response.sendRedirect("delete_account.jsp?success=false");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print exception details to console
            response.sendRedirect("delete_account.jsp?success=false&error=" + ex.getMessage());
        }
    }
}
