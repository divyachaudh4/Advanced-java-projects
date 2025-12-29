package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
  public static Connection getMyconn() throws ClassNotFoundException, SQLException {
	  Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db","root","root");
		
		return c;
  }
}
  
  
