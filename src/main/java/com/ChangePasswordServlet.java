package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        Customerdao1 customerdao1 = new Customerdao1();
        if (customerdao1.changePassword(accountNumber, oldPassword, newPassword)) {
            HttpSession session = request.getSession();
            session.setAttribute("changePasswordSuccess", "true");
            response.sendRedirect("Customerlogin.jsp");
        } else {
            response.sendRedirect("Changepassword.jsp?error=Invalid credentials");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Changepassword.jsp");
    }
}
