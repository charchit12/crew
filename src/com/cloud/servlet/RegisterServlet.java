package com.cloud.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(name = "Register", urlPatterns = { "/Register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int age=Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String country = request.getParameter("country");
		
		String errorMsg = null;
	
		if(email == null || email.equals("")){
			errorMsg = "Email ID can't be left blank.";
		}
		if(password == null || password.equals("")){
			errorMsg = "Password can't be left blank.";
		}
		if(fname == null || fname.equals("")){
			errorMsg = "fName can't be left blank.";
		}
		if(lname == null || lname.equals("")){
			errorMsg = "lName can't be left blank.";
		}
		if(country == null || country.equals("")){
			errorMsg = "Country can't be left blank.";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into trainer(fname,lname,age,email,password,country) values (?,?,?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setInt(3, age);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.setString(6, country );
			
			ps.execute();
			
			logger.info("User registered with email="+email);
			
			//forward to login page to login
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>Registration successful, please login below.</font>");
			rd.include(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection problem");
			throw new ServletException("DB Connection problem.");
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("SQLException in closing PreparedStatement");
			}
		}
		}
		
	}

}
