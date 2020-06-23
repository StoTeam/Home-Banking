package com.stoteam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DbConnection {



	public static synchronized Connection Connect() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("F_3*phz?=M");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("dbhome");
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		}

		//		try {
		//			conn = DriverManager.getConnection("jdbc:mysql://localhost/stabanca!?user=root&password=gianluca");
		//			System.out.println("Connesso al DB");
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//		}
		//		return conn;
	}	

	//	public static void closeConnect() {
	//		try {
	//			conn.close();
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}

}
