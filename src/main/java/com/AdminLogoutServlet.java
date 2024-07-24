package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // do not create session if it doesn't exist
        
        if (session != null) {
            session.setAttribute("sessionExpired", "You have successfully logged out.");
            session.invalidate(); // invalidate the session
        }
        
        response.sendRedirect("AdminLogin.jsp"); // redirect to login page
    }
}
