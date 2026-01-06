package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.dbconnection.DBConnection;

public class GetAllStudentServlet  extends GenericServlet{
	private Connection con =null;

	public void init() {
		try {
			con=DBConnection.getMyDBConnection();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		try {
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			Statement stm =con.createStatement();
			ResultSet rs=stm.executeQuery("select * from studentApp");

			pw.print("<table style ='border:2px solid black'><tr>"
					+ "<th>studentID</th>"
					+ "<th>studentName</th>"
					+ "<th>studentEmail</th>"
					+ "<th>studentAddress</th>"
					+ "<th>studentMno</th></tr>");

			while(rs.next()) {	
				pw.print("<tr><td>"+rs.getInt(1)+"</td>"
						+ "<td>" +rs.getString(2)+"</td>"
						+ "<td>" +rs.getString(3)+"</td>"
						+ "<td>" +rs.getString(4)+"</td>"
						+ "<td>" +rs.getString(5)+"</td>"
						+ "<td>" +rs.getInt(1)+"</td></tr><br>");
			}
			pw.print("</table>");


		}catch(SQLException e) {

			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}



	}

}
