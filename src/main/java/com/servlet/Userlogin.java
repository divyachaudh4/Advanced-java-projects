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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.User;
import com.db.DBConnection;
@WebServlet("/userlogin")
public class Userlogin extends GenericServlet
{
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		try {

			String email=request.getParameter("uemail");
			String password=request.getParameter("upass");

			Connection c=DBConnection.getMyconn();
			PreparedStatement ps= c.prepareStatement("select * from user_details where  uemail=? and upass=? ");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{

				User user = new User();

				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setAddress(rs.getString(4));
				user.setPassword(rs.getString(5));

				request.setAttribute("loggeduser", user);

				RequestDispatcher rd = request.getRequestDispatcher("allproduct");
				rd.forward(request, response);
			}else {
				request.setAttribute("Message", "please check email and password");
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response); 

			}
		} catch (Exception e) {
			e.printStackTrace();


		}
	}

}
