package com.cloud.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class LogoutServlet extends HttpServlet 
{

    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(LogoutServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if (session != null) 
		{
            session.removeAttribute("loginId");
            session.invalidate();
        }
        request.setAttribute("action", "Login");
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        request.setAttribute("date", dateFormat.format(date));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
        rd.include(request, response);
    }
}
