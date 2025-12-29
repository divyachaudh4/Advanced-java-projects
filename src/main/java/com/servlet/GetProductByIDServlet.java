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

import com.bean.Product;
import com.db.DBConnection;
import com.sun.net.httpserver.Authenticator.Result;
@WebServlet("/getproductbyid")
public class GetProductByIDServlet extends GenericServlet {
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

	       try {
			int pid = Integer.parseInt(request.getParameter("pid"));
			Connection c = DBConnection.getMyconn();
			PreparedStatement ps = c.prepareStatement("select * from add_details where pid=?");
			
			ps.setInt(1, pid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Product p= new Product();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setPqty(rs.getInt(3));
				p.setPrice(rs.getString(4));
				
				request.setAttribute("existingproduct", p);
				RequestDispatcher rd =request.getRequestDispatcher("editproduct.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("Message", "Product not found with pid");
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}

			
		} catch (Exception e) {
			
		}
	}

}
