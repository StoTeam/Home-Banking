package com.stoteam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	static Connection conn = null;

	public static Connection Connect() {		
		try {
			conn = DriverManager.getConnection(System.getenv("DB_CONNECT"));
			System.out.println("Connesso al DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}	
	
	public static void closeConnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
