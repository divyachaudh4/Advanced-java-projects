package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.db.DBConnection;
@WebServlet("/adminlogin")
public class AdminLogin extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		try {
			
		  String Email = req.getParameter("email");
		  String Password= req.getParameter("pass");
		  
		   Connection c = DBConnection.getMyconn();
		   PreparedStatement ps = c.prepareStatement("select * from hospital_details where email=? and pass=?");
		   ps.setString(1, Email);
		   ps.setString(2, Password);
		   
		   ResultSet rs = ps.executeQuery();
		   
		  if(rs.next()) {
			  RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
			  rd.forward(req, res);
		  }else {
			  req.setAttribute("Message", "Admin Login Unsuccessful");
			  RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			  rd.forward(req, res);
		  }
			  
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
