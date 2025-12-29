package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.bean.Product;
import com.db.DBConnection;
@WebServlet("/allproduct")
public class GetAllServlet extends GenericServlet{
	
	private Connection c = null;
	

	public void init() {
		try {
			 c=DBConnection.getMyconn();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}	 

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		try {	
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");
			Statement smt = c.createStatement();
			ResultSet rs=smt.executeQuery("select * from add_details");
			ArrayList<Product> al = new ArrayList<Product>();

			while(rs.next()) {
				Product p = new Product();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setPqty(rs.getInt(3));
				p.setPrice(rs.getString(4));
				al.add(p);

			}
			request.setAttribute("allproduct", al);
			RequestDispatcher rd = request.getRequestDispatcher("productdashboard.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}  

	}

}
