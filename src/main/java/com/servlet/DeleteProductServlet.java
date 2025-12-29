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
@WebServlet("/deleteproduct")
public class DeleteProductServlet extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		try {
			
		     int pid=Integer.parseInt(request.getParameter("pid"));
		     Connection c=DBConnection.getMyconn();
		     
		     PreparedStatement ps = c.prepareStatement("delete from add_details where pid=?");
			 ps.setInt(1, pid);
			 
			 int result = ps.executeUpdate();
			 if(result==1) {
					RequestDispatcher rd =request.getRequestDispatcher("allproduct");
					rd.forward(request, response);
				}else {
					request.setAttribute("Message", "Product not deleted");
					RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
					rd.forward(request, response);
				}

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
