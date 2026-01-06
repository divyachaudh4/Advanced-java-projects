package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.db.DBConnection;

@WebServlet("/appointment")
public class Appointment  extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		try {
			
			String Patient_Name = req.getParameter("pname");
			String Patient_Address = req.getParameter("paddress");
			String Patient_Mno  = req.getParameter("pno");
			String Patient_disease = req.getParameter("pdisease");
			
			Connection c = DBConnection.getMyconn();
			
			PreparedStatement ps = c.prepareStatement("insert into appointment values(?,?,?,?)");
			ps.setString(1, Patient_Name);
			ps.setString(2, Patient_Address);
			ps.setString(3, Patient_Mno);
			ps.setString(4, Patient_disease);
			 
			int result = ps.executeUpdate();
			
            if(result==1) {
            	     pw.println(Patient_Name);
            	     pw.println(Patient_Address);
            	     pw.println(Patient_Mno);
            	     pw.println(Patient_disease);
            }else {
            	   req.setAttribute("Message", "Appointment is not done");
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
