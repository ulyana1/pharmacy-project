package com.pharmacy.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DataContext {
	
	private String dbname = "pharmacy_db";
	private Connection conn;
	private Class driver;
	private String user = "root";
	private String passwd = "toor";
	public DataContext(){
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager
			        .getConnection("jdbc:mysql://localhost/"+dbname+"?useSSL=false",user,passwd);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection(){	
		return conn;
	}
	
}
