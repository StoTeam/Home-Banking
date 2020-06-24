package com.stoteam.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DbConnection {

	public static synchronized Connection Connect() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("gianluca");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("stabanca!");
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
