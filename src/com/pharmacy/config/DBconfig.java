package com.pharmacy.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class DBconfig {
	private String dbname = "pharmacy_db";
	private Connection conn;
	private String user = "root";
	private String passwd = "1111";
	public DBconfig(){
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+dbname+"?useSSL=false",user,passwd);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection(){	
		return conn;
	}
}
