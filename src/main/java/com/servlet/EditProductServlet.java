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
@WebServlet("/editproduct")
public class EditProductServlet  extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		try {
			int pid =Integer.parseInt( request.getParameter("pid"));
			String pname=request.getParameter("pname");
			int pQty=Integer.parseInt(request.getParameter("pqty"));
			String price = request.getParameter("price");
			
			Connection c = DBConnection.getMyconn();
			PreparedStatement ps =c.prepareStatement("update add_details set pname=?,pqty=?,price=? where pid=?");
			ps.setString(1, pname);
			ps.setInt(2, pQty);
			ps.setString(3, price);
			ps.setInt(4, pid);
			
			int result = ps.executeUpdate();
			if(result==1) {
				RequestDispatcher rd =request.getRequestDispatcher("allproduct");
				rd.forward(request, response);
			}else {
				request.setAttribute("Message", "Product not updated with pid");
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		
	}

}
