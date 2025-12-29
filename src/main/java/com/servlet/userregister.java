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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnection;
@WebServlet("/newuserregister")
public class userregister extends GenericServlet {
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
	          PrintWriter pw = response.getWriter();
	          response.setContentType("text/html");
		try {
			int userid= Integer.parseInt(request.getParameter("uid"));
			String name=request.getParameter("uname");
			String email=request.getParameter("uemail");
			String address=request.getParameter("uaddre");
			String password=request.getParameter("upass");
			
			
			Connection c= DBConnection.getMyconn();
			PreparedStatement ps= c.prepareStatement("insert into user_details values(?,?,?,?,?)");
			ps.setInt(1, userid);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, password);
			
			int result=ps.executeUpdate();
			
			if(result==1) {
				
				pw.print("User Register Successfully");
				RequestDispatcher rd=request.getRequestDispatcher("userlogin.jsp");
				rd.include(request, response);
			}else {
				pw.print("User Not Register Successfully");
				request.setAttribute("Message", "User not register please correct details");
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
