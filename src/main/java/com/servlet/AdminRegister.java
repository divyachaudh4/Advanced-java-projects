package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.db.DBConnection;
@WebServlet("/adminregister")
public class AdminRegister  extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		try {
			String Name=req.getParameter("name");
			String Email=req.getParameter("email");
			String Password=req.getParameter("pass");
			
			Connection c = DBConnection.getMyconn();
			PreparedStatement ps= c.prepareStatement("insert into hospital_details values (?,?,?)");
			ps.setString(1,Name );
			ps.setString(2, Email );
			ps.setString(3, Password);
			
			int result = ps.executeUpdate();
			if(result==1) {
				pw.print("Admin Register Succesfully");
				RequestDispatcher rd= req.getRequestDispatcher("login.jsp");
				rd.include(req, res);
			}else {
				pw.print("Admin Not Register something went wrong");
				req.setAttribute("Message", "Admin Registration fail plz try again");
				RequestDispatcher rd=req.getRequestDispatcher("error.jsp");
				rd.forward(req, res);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}
}

		
		