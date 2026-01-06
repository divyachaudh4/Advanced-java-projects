package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.dbconnection.DBConnection;

public class StudentRegisterServlet extends GenericServlet {
	private Connection c = null;
	public void init() {
		try {
			c = DBConnection.getMyDBConnection();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}


	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		try {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");


		int studentID=Integer.parseInt(req.getParameter("studentID"));
		String studentName=req.getParameter("studentName");
		String studentEmail=req.getParameter("studentEmail");
		String studentAddress=req.getParameter("studentAddress");
		int studentMno=Integer.parseInt(req.getParameter("studentMno"));


		PreparedStatement ps = c.prepareStatement("insert into studentApp values(?,?,?,?,?)");
		ps.setInt(1, studentID);
		ps.setString(2, studentName);
		ps.setString(3, studentEmail);
		ps.setString(4, studentAddress);
		ps.setInt(5, studentMno);
		
		int result = ps.executeUpdate();

		if(result==1) {
			pw.println("new Student registration successfully");
		}
		else {
			pw.println("New Student not registered successfully");
		}}catch (Exception e) {
			e.printStackTrace();
		}
	}


public void destroy() {
	try {
		c.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
