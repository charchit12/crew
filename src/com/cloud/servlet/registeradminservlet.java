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

@WebServlet(name = "registeradmin", urlPatterns = { "/registeradmin" })
public class registeradminservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int aid=Integer.parseInt(request.getParameter("aid"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
				
		String errorMsg = null;
	
		if(email == null || email.equals("")){
			errorMsg = "Email ID can't be left blank.";
		}
		if(password == null || password.equals("")){
			errorMsg = "Password can't be left blank.";
		}
		if(name == null || name.equals("")){
			errorMsg = "fName can't be left blank.";
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
			ps = con.prepareStatement("insert into admin(aid,name,email,password) values (?,?,?,?)");
			ps.setInt(1, aid);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			
			ps.execute();
			
			logger.info("User registered with email="+email);
			
			//forward to login page to login
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminlogin.html");
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
