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

@WebServlet(name = "feedback", urlPatterns = { "/feedback"})
public class feedbackservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(feedbackservlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String feedback = request.getParameter("feedback");
		
		String errorMsg = null;
	
		if(email == null || email.equals("")){
			errorMsg = "Email ID can't be left blank.";
		}
		
		if(fname == null || fname.equals("")){
			errorMsg = "fName can't be left blank.";
		}
		if(lname == null || lname.equals("")){
			errorMsg = "lName can't be left blank.";
		}
		if(feedback == null || feedback.equals("")){
			errorMsg = "feedback can't be left blank.";
		}
		
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/feedback.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{
		
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into feedback(fname,lname,email,feedback) values (?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, feedback );
			
			ps.execute();
			
			logger.info("User registered with email="+email);
			
			//forward to login page to login
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/feedback.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=green>Feedback Registered successfully.</font>");
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
